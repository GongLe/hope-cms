package org.hope.core.entity.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hope.core.entity.AuditorEntity;
import org.hope.core.entity.permission.Permission;
import org.hope.core.entity.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Role Entity User: Gongle Date: 13-9-9 Time: 下午4:52
 */
@Entity
@Table(name = "ss_role")
public class Role extends AuditorEntity {
    private String name;
    private String code;
    private String status;
    private String type;
    private String description;
    private List<User> users = new ArrayList<User>();
    private List<Permission> permissions = new ArrayList<Permission>();

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @JsonIgnore
    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL})
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
