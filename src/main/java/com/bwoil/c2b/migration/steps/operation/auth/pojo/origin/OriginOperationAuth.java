package com.bwoil.c2b.migration.steps.operation.auth.pojo.origin;

public class OriginOperationAuth {
    // ID
    private Integer authId;

    // 应用市场
    private String plat;

    // 版本
    private String version;

    // 说明
    private String description;

    // 版本MD5值
    private String auth;

    // 添加时间
    private Long addTime;

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }


    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
