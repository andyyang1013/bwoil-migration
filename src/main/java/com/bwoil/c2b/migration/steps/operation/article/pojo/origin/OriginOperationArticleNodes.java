package com.bwoil.c2b.migration.steps.operation.article.pojo.origin;

public class OriginOperationArticleNodes {

    // 节点id
    private Integer nodeId;

    // 父节点  默认：0
    private Integer parentId;

    // 节点深度  默认：0
    private long nodeDepth;

    // 节点名称
    private String nodeName;

    // 节点页面名
    private String nodePagename;

    // 节点路径
    private String nodePath;

    // SEO标题
    private String seoTitle;

    // SEO关键字
    private String seoKeywords;

    // 是否存在子节点  默认：false
    private String hasChildren;

    // 发布  默认：false
    private String ifpub;

    // 图  默认：false
    private String hasimage;

    // 排序  默认：0
    private Integer ordernum;

    // 主页  默认：false
    private String homepage;

    // 修改时间
    private long uptime;

    // 单独页模板
    private String tmplPath;

    // 列表页模板
    private String listTmplPath;

    private String disabled;

    // SEO简介
    private String seoDescription;

    // 文章内容
    private String content;

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
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

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    public String getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getIfpub() {
        return ifpub;
    }

    public void setIfpub(String ifpub) {
        this.ifpub = ifpub;
    }

    public String getHasimage() {
        return hasimage;
    }

    public void setHasimage(String hasimage) {
        this.hasimage = hasimage;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public long getUptime() {
        return uptime;
    }

    public void setUptime(long uptime) {
        this.uptime = uptime;
    }

    public String getTmplPath() {
        return tmplPath;
    }

    public void setTmplPath(String tmplPath) {
        this.tmplPath = tmplPath;
    }

    public String getListTmplPath() {
        return listTmplPath;
    }

    public void setListTmplPath(String listTmplPath) {
        this.listTmplPath = listTmplPath;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
