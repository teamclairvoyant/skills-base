package com.clairvoyant.clarise.model.superclass;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class Persistable extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @NotNull
    @Column(nullable = false, length = 36)
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @PrePersist
    void onCreate() throws Exception {
        super.onCreate();
    }
}
