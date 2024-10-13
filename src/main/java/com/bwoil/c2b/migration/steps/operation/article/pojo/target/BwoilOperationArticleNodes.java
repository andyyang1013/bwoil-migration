package com.bwoil.c2b.migration.steps.operation.article.pojo.target;


import java.util.Date;

public class BwoilOperationArticleNodes {

    private long nodeId;
    private long parentId;
    private long nodeDepth;
    private String nodeName;
    private String nodePagename;
    private String nodePath;
    private String hasChildren;
    private String seoTitle;
    private String seoDescription;
    private String seoKeywords;
    private String ifpub;
    private long ordernum;
    private String homepage;
    private String content;
    private String status;
    private Date uptime;
    private Date createTime;


    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }


    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }


    public long getNodeDepth() {
        return nodeDepth;
    }

    public void setNodeDepth(long nodeDepth) {
        this.nodeDepth = nodeDepth;
    }


    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }


    public String getNodePagename() {
        return nodePagename;
    }

    public void setNodePagename(String nodePagename) {
        this.nodePagename = nodePagename;
    }


    public String getNodePath() {
        return nodePath;
    }

    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }


    public String getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }


    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }


    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }


    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }


    public String getIfpub() {
        return ifpub;
    }

    public void setIfpub(String ifpub) {
        this.ifpub = ifpub;
    }


    public long getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(long ordernum) {
        this.ordernum = ordernum;
    }


    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
