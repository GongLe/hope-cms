package org.lework.core.entity.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.lework.core.entity.AuditorEntity;
import org.lework.core.entity.role.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单Entity
 * User: Gongle
 * Date: 13-10-22
 */
@Entity
@Table(name = "SS_MENU")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Menu extends AuditorEntity {

    private String name;            //菜单名
    private String code;            //代码
    private Integer sort;            //排序
    private String type = "menu";        //类型  menu,url
    private String status;        //状态 默认为有效状态
    private String url;                //URL
    private String icon;                //图标
    private Menu parentMenu;        //上级菜单
    private List<Menu> childrenMenus;    //下级菜单
    private List<Role> roles = new ArrayList<Role>();    //菜单对应的角色

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_parent_menu_id")
    public Menu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "parentMenu", fetch = FetchType.LAZY)
    public List<Menu> getChildrenMenus() {
        return childrenMenus;
    }

    public void setChildrenMenus(List<Menu> childrenMenus) {
        this.childrenMenus = childrenMenus;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "menus")
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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
