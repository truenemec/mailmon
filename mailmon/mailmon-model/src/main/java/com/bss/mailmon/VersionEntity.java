package com.bss.mailmon;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.AccessType.Type;

@MappedSuperclass
@AccessType(Type.PROPERTY)
public abstract class VersionEntity extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    private Integer version;

    @Version
    @Column(name = "VERSION")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
