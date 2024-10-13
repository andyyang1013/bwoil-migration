package com.bwoil.c2b.migration.steps.desktop.pojo.origin;


public class OriginDesktopUsers {

  private Long uid;
  private String account;
  private String password;
  private String name;
  private Long isSuper;
  private Long loginCount;
  private Long lastLoginTime;
  private Long disabled;
  private String opNo;
  private String memo;
  private String avatar;
  private Long deleteStatus;

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getIsSuper() {
    return isSuper;
  }

  public void setIsSuper(Long isSuper) {
    this.isSuper = isSuper;
  }

  public Long getLoginCount() {
    return loginCount;
  }

  public void setLoginCount(Long loginCount) {
    this.loginCount = loginCount;
  }

  public Long getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(Long lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

  public Long getDisabled() {
    return disabled;
  }

  public void setDisabled(Long disabled) {
    this.disabled = disabled;
  }

  public String getOpNo() {
    return opNo;
  }

  public void setOpNo(String opNo) {
    this.opNo = opNo;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public Long getDeleteStatus() {
    return deleteStatus;
  }

  public void setDeleteStatus(Long deleteStatus) {
    this.deleteStatus = deleteStatus;
  }
}
