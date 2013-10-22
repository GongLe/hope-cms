package org.hope.core.entity.organization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hope.core.entity.AuditorEntity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 组织机构Entity
 * User: Gongle
 * Date: 13-10-22
 */
@Entity
@Table(name = "SS_ORGANIZATION")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Organization extends AuditorEntity {

    /** 组织代码**/
    private String code;

    /** 组织名称**/
    private String name;

    /**组织简称*/
    private String shortName;

    /** 排序**/
    private Integer sort = 0;

    /** 主管姓名**/
    private String manager;

    /**联系电话**/
    private String phone;

    /**传真**/
    private String fax;

    /** 组织机构类型编码**/
    private String type;

    /**系统内部编码*/
    private String syscode;

    /**组织状态**/
    private String status  ;

    /**
     * 是否是有子级  0:false,1:true
     * ps:本来是用JPA OneToMany getChildren,出于效率考虑,直接用字段标识是否为叶子节点.
     */
    private Integer isleaf  ;

    /**
     * 上级组织
     */
    private Organization parentOrganization;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSyscode() {
        return syscode;
    }

    public void setSyscode(String syscode) {
        this.syscode = syscode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "is_leaf")
    public Integer getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(Integer isleaf) {
        this.isleaf = isleaf;
    }
    @JsonIgnore
    @ManyToOne
    @JoinTable(
            name = "SS_ORGANIZATION_ORGANIZATION",
            joinColumns = {@JoinColumn(name = "FK_ORGANIZATION_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "FK_PARENT_ORGANIZATION_ID", referencedColumnName = "ID")
            })
    public Organization getParentOrganization() {
        return parentOrganization;
    }

    public void setParentOrganization(Organization parentOrganization) {
        this.parentOrganization = parentOrganization;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("code", code)
                .append("name", name)
                .append("status", status)
                .toString();
    }
}
