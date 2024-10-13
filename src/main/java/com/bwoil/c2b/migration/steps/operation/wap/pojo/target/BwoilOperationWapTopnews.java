package com.bwoil.c2b.migration.steps.operation.wap.pojo.target;


import java.util.Date;

public class BwoilOperationWapTopnews {

    private long id;
    private String author;
    private String title;
    private String thumbnail;
    private String context;
    private String ison;
    private String appProid;
    private String status;
    private Date editTime;
    private Date addTime;
    private String msiteProid;
    private long baseQty;
    private long readQty;
    private long likeQty;
    private long sort;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }


    public String getIson() {
        return ison;
    }

    public void setIson(String ison) {
        this.ison = ison;
    }


    public String getAppProid() {
        return appProid;
    }

    public void setAppProid(String appProid) {
        this.appProid = appProid;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }


    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }


    public String getMsiteProid() {
        return msiteProid;
    }

    public void setMsiteProid(String msiteProid) {
        this.msiteProid = msiteProid;
    }


    public long getBaseQty() {
        return baseQty;
    }

    public void setBaseQty(long baseQty) {
        this.baseQty = baseQty;
    }


    public long getReadQty() {
        return readQty;
    }

    public void setReadQty(long readQty) {
        this.readQty = readQty;
    }


    public long getLikeQty() {
        return likeQty;
    }

    public void setLikeQty(long likeQty) {
        this.likeQty = likeQty;
    }


    public long getSort() {
        return sort;
    }

    public void setSort(long sort) {
        this.sort = sort;
    }

}
