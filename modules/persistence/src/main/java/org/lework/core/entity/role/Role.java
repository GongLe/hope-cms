package org.lework.core.entity.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.lework.core.entity.AuditorEntity;
import org.lework.core.entity.menu.Menu;
import org.lework.core.entity.permission.Permission;
import org.lework.core.entity.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Role Entity
 * user: Gongle
 * Date: 13-9-9
 */
@Entity
@Table(name = "SS_ROLE")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends AuditorEntity {
    /**角色名称**/
    private String name;
    /**角色代码**/
    private String code;
    private String status;
    private String description;
    private List<User> users = new ArrayList<User>();
    private List<Permission> permissions = new ArrayList<Permission>();
    private List<Menu> menus = new ArrayList<Menu>() ;

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Length(max = 200 )
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "roles",
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE}  )
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @JoinTable(name = "SS_ROLE_PERMISSION",
            joinColumns =
            @JoinColumn(name = "FK_ROLE_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "FK_PERMISSION_ID", referencedColumnName = "ID")
    )
    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @JoinTable(name = "SS_ROLE_MENU",
            joinColumns =
            @JoinColumn(name = "FK_ROLE_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "FK_MENU_ID", referencedColumnName = "ID")
    )
    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
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
