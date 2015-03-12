package com.bss.mailmon.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.AccessType.Type;

import com.bss.mailmon.VersionEntity;

@Entity
@Table(name = "PR_USER_DATA")
@AccessType(Type.PROPERTY)
public class UserEntity extends VersionEntity {
    private static final long serialVersionUID = 1L;
    private String lastname;
    private String firstname;
    private String middlename;
    private String login;

    public UserEntity() {
    }

    public UserEntity(UserEntity entity) {
    }

    @Column(name = "LASTNAME")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "FIRSTNAME")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(name = "MIDDLENAME")
    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    @Column(name = "LOGIN")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PR_USER_DATA_GEN")
    @SequenceGenerator(sequenceName = "PR_USER_DATA_ID_SEQ", name = "PR_USER_DATA_GEN")
    public Long getId() {
        return super.getId();
    }

}
