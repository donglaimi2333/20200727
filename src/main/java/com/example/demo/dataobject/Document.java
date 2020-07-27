package com.example.demo.dataobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Document {
    private Integer id;
    /***/
    private String title;

    private String summary;

    private String label;
    /***/
    private Long user;
    /**
     * 阅读量
     */
    private Integer readingVolume;
    /**
     * 下载量
     */
    private Integer downloads;
    /**
     * 是否支持下载：1 支持，0 不支持
     */
    private Integer supportDownload;
    /**
     * 是否支持引用 1 支持，0 不支持
     */
    private Integer supportReference;
    /**
     * 被引用文章的ID
     */
    private Integer referenceId;
    /**
     * 文档分类
     */
    private Integer classification;
    /**
     * 文件大小
     */
    private Long size;
    /**
     * 文件下载地址
     */
    private String link;
    /**
     * 源文件下载地址
     */
    private String sourceLink;
    /**
     * 标识文章状态 1：正常，0：隐藏
     */
    private Integer state;
    /**
     * 数据逻辑删除字段 1：数据正常，0：数据被删除
     */
    private Integer status;
    /**
     * 文件转码状态 0:未完成 1:已完成
     */
    private Integer convertStatus;
    /***/
    private Long modifyTime;
    /***/
    private Long createTime;
}
