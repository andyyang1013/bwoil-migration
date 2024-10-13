package com.bwoil.c2b.migration.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 时间戳工具类
 */
public class TimeStampUtil {

    private static final Timestamp DEFAULT_TIME = Timestamp.valueOf("1990-01-01 08:00:00");

    /**
     * 获取时间戳,非法时间返回为默认时间
     *
     * @param sTime 时间戳秒数
     * @return 时间戳
     */
    public static Timestamp getTimestampWithDefault(Long sTime) {
        if (sTime == null) {
            return DEFAULT_TIME;
        }
        Timestamp timestamp = new Timestamp(sTime * 1000);
        Timestamp epoch = Timestamp.from(Instant.EPOCH);
        if (timestamp.after(epoch)) {
            return timestamp;
        }
        return DEFAULT_TIME;
    }

    /**
     * 获取时间戳,非法时间返回为默认时间
     *
     * @param sTime 时间戳秒数
     * @return 时间戳
     */
    public static Timestamp getTimestampWithNull(Long sTime) {
        if (sTime == null) {
            return null;
        }
        Timestamp timestamp = new Timestamp(sTime * 1000);
        Timestamp epoch = Timestamp.from(Instant.EPOCH);
        if (timestamp.after(epoch)) {
            return timestamp;
        }
        return null;
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public static Timestamp now() {
        return Timestamp.valueOf(LocalDateTime.now());
    }
    
    /**
     * 时间戳转化为 yyyy-MM-dd HH:mm:ss 格式
     */
    public static String convertDate(Long time) {
    	if(time == null || time == 0) {
    		return "0000-00-00 00:00:00";
    	}
    	
    	return  DateFormatUtils.format(new Date(time*1000), "yyyy-MM-dd HH:mm:ss");
    }
    /**
     * 时间戳转化为 yyyy-MM-dd HH:mm:ss 格式
     */
    public static String convertDate(String time) {
    	if(StringUtils.isEmpty(time)) {
    		return "0000-00-00 00:00:00";
    	}
    	
    	return  convertDate(new Long(time));
    }
}
