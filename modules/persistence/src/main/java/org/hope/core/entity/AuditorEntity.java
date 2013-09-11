package org.hope.core.entity; import org.springframework.data.annotation.CreatedBy; import org.springframework.data.annotation.CreatedDate; import org.springframework.data.annotation.LastModifiedBy; import org.springframework.data.annotation.LastModifiedDate; import javax.persistence.MappedSuperclass; import java.util.Date; /** Spring data jpa entity 审计接口 User: Gongle Date: 13-9-1 Time: 上午10:41 */ @MappedSuperclass public class AuditorEntity extends IdEntity implements Cloneable { @CreatedBy private String createdBy; @CreatedDate private Date createdDate; @LastModifiedBy private String lastModifiedBy; @LastModifiedDate private Date lastModifiedDate; public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}
