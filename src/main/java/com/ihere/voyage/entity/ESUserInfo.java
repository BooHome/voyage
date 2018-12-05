package com.ihere.voyage.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: fengshibo
 * @date: 2018/9/30 09:55
 * @description:
 */
@Document(indexName = "voyage",type = "user")
public class ESUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 编号 */
    private Long id;
    /** 姓名 */
    private String name;

    /** 年龄 */
    private Integer age;

    /** 描述 */
    private String description;

    /** 创建时间 */
    private Date createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
