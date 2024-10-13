package com.bwoil.c2b.migration.steps.member;

import com.bwoil.c2b.migration.core.builder.StepBuilder;
import com.bwoil.c2b.migration.core.builder.StepReaderBuilder;
import com.bwoil.c2b.migration.core.builder.StepWriterBuilder;
import com.bwoil.c2b.migration.steps.member.pojo.origin.OriginMember;
import com.bwoil.c2b.migration.steps.member.pojo.target.BwoilMember;
import com.bwoil.c2b.migration.utils.AES;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.Date;

@Configuration("bwoilMemberMigration")
public class BwoilMemberMigration {

    public final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BwoilMemberMigration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("bwoilMemberMigrationStep")
    public Step step(@Qualifier("originDataSource") DataSource dataSource, @Qualifier("targetDataSource") DataSource targetDataSource) {
        return new StepBuilder<OriginMember, BwoilMember>().factory(stepBuilderFactory).name("会员服务-会员账号-历史数据迁移")
                .reader(reader(dataSource)).processor(processor()).writer(writer(targetDataSource))
                .build();
    }

    @Bean("bwoilMemberMigrationReader")
    public ItemReader<OriginMember> reader(@Qualifier("originDataSource") DataSource dataSource) {
        String sql = "select a.member_id ,a.login_account mobile ,a.password_account register_mobile,a.login_password password,a.country_code,a.createtime register_time," +
                " c.true_name realname,b.realname_auth ,b.avatar image_id,b.shop_member_bn shop_bn,b.openid wechat_opid,d.transaction_pass_hash trade_pwd,b.unames," +
                "b.source,b.market_source,b.pcode,b.ad_source,b.extra_channel_id, b.disabled,c.iden_number_encrypt iden_code,a.disabled status ,b.webank_second_account yun_acct_flag,b.webank_createtime yun_acct_time " +
                "from sdb_pam_members a " +
                "left join sdb_b2c_members b on a.member_id = b.member_id " +
                "left join sdb_b2c_transaction_pass d on a.member_id = d.member_id " +
                "left join (select member_id,true_name,iden_number_encrypt from sdb_b2c_member_identity_info where audit_status='1' GROUP BY member_id ORDER BY iden_id DESC) c on a.member_id = c.member_id " +
                " where a.login_type='mobile' ";
//                "and a.login_account in ('18970147183') ";
        return new StepReaderBuilder<OriginMember>().dataSource(dataSource).sql(sql).mappedClass(OriginMember.class).multithreaded(true).sortKeys("a.member_id").build();
    }

    @Bean(name = "bwoilMemberMigrationProcessor")
    public ItemProcessor<OriginMember, BwoilMember> processor() {
        return item -> {
            BwoilMember target = new BwoilMember();
            BeanUtils.copyProperties(item, target);

            if (item.getRegisterMobile().length() > 11) {
                target.setRegisterMobile(item.getMobile());
            }
//            if(!StringUtils.isEmpty(item.getIdenCode())){
//            }
            if (item.getIdenCode() != null) {
//                System.out.println(item.getIdenCode());
                target.setIdenCode(AES.encrypt(AES.decrypt(item.getIdenCode())));
            }

            Long timestamp = item.getRegisterTime() * 1000L;
            Date date = new Date(timestamp);
            Timestamp registerTime = new Timestamp(date.getTime());
            registerTime.setNanos(0);
            target.setRegisterTime(registerTime);
            target.setLastUpdateTime(new Date());

            String realNameState = "true".equals(item.getRealnameAuth()) ? "1" : "0";
            target.setRealnameAuth(realNameState);

            String status = "false".equals(item.getStatus()) ? "0" : "1";
            target.setStatus(status);
            if (target.getPcode() != null && target.getPcode().length() > 20) {
                target.setPcode(target.getPcode().substring(0, 20));
            }
            if("init".equals(item.getYunAcctFlag())){
                target.setYunAcctFlag("0");
            }else if("true".equals(item.getYunAcctFlag())){
                target.setYunAcctFlag("1");
            }else if("auditing".equals(item.getYunAcctFlag())){
                target.setYunAcctFlag("2");
            }else if("false".equals(item.getYunAcctFlag())){
                target.setYunAcctFlag("3");
            }

            if(item.getYunAcctTime()!=null){
                Long yunAccTime = item.getYunAcctTime() * 1000L;
                Date yunAccDate = new Date(yunAccTime);
                target.setYunAcctTime(yunAccDate);
            }

            return target;
        };
    }

    @Bean(name = "bwoilMemberMigrationWriter")
    public ItemWriter<BwoilMember> writer(@Qualifier("targetDataSource") DataSource dataSource) {
        String sqlStr = "INSERT INTO bwoil_member (member_id, mobile, register_mobile, country_code, image_id, realname, nick_name, sex, email, wechat_opid, realname_auth, auth_channel, auth_time, password, trade_pwd, yun_acct_flag,yun_acct_time, iden_code, shop_bn, pcode, remark, source, market_source, ad_source, extra_channel_id, register_time, last_update_time, status) " +
                "VALUES (:memberId,:mobile,:registerMobile,:countryCode,:imageId,:realname,:nickName,:sex,:email,:wechatOpid,:realnameAuth,:authChannel,:authTime,:password,:tradePwd,:yunAcctFlag,:yunAcctTime,:idenCode,:shopBn,:pcode,:remark,:source,:marketSource,:adSource, :extraChannelId, :registerTime,:lastUpdateTime,:status)";
        return new StepWriterBuilder<BwoilMember>().dataSource(dataSource).sql(sqlStr).build();
    }
}
