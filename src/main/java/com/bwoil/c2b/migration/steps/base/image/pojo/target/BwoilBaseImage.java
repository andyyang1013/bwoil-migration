package com.bwoil.c2b.migration.steps.base.image.pojo.target;


public class BwoilBaseImage {

    private String imageId;
    private String url;
    private java.sql.Timestamp lastModified;


    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public java.sql.Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(java.sql.Timestamp lastModified) {
        this.lastModified = lastModified;
    }

}
