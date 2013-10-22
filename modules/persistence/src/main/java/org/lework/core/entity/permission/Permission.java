package org.lework.core.entity.permission;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.lework.core.entity.AuditorEntity;
import org.lework.core.entity.role.Role;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Permission Entity
 * User: Gongle
 * Date: 13-9-9
 * Time: 下午4:52
 */
@Entity
@Table(name = "SS_PERMISSION")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Permission extends AuditorEntity {
    private String name;
    private String code;
    private String status;
    private String type;
    private String description;
    private List<Role> roles = new ArrayList<Role>();

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
    @ManyToMany(mappedBy = "permissions", cascade = CascadeType.ALL)
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
