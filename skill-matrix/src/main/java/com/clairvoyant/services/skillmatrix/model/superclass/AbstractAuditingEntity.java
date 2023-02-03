package com.clairvoyant.services.skillmatrix.model.superclass;

import com.clairvoyant.services.skillmatrix.configuration.constants.CommonConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Base abstract class for entities which will hold definitions for created, modified by and
 * created, modified by date.
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 36, updatable = false)
    @JsonIgnore
    @NotNull
    private String createdBy;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    @JsonIgnore
    @NotNull
    private ZonedDateTime createdDate;

    @LastModifiedBy
    @Column(name = "modified_by", length = 36, nullable = false)
    @JsonIgnore
    @NotNull
    private String modifiedBy;

    @LastModifiedDate
    @Column(name = "modified_date", nullable = false)
    @JsonIgnore
    @NotNull
    private ZonedDateTime modifiedDate;

    @PrePersist
    void onCreate() throws Exception {
        SecurityContext context = SecurityContextHolder.getContext();
        this.setCreatedDate(getUtcZonedDateTime());
        this.setModifiedDate(getUtcZonedDateTime());

        // TODO : Set proper condition for checking if SecurityContext is having the required auth data.
        if (Objects.nonNull(context.getAuthentication())) {
            // TODO : Set proper data after implementing SecurityContext and having auth user in it.
            this.setCreatedBy(context.getAuthentication().getName());
            this.setModifiedBy(context.getAuthentication().getName());
        } else {
            throw new Exception(CommonConstants.AUDIT_TRAIL_EXCEPTION);
        }
    }

    public static ZonedDateTime getUtcZonedDateTime() {
        return ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));
    }

    @PreUpdate
    void onPersist() throws Exception {
        SecurityContext context = SecurityContextHolder.getContext();
        this.setModifiedDate(getUtcZonedDateTime());
        if (Objects.nonNull(context.getAuthentication())) {
            // TODO : Set proper data after implementing SecurityContext and having auth user in it.
            this.setModifiedBy(context.getAuthentication().getName());
        } else {
            throw new Exception(CommonConstants.AUDIT_TRAIL_EXCEPTION);
        }
    }
}

