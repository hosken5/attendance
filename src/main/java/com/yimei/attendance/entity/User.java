package com.yimei.attendance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;


public class User extends BaseEntity implements Serializable {

    private Long id;
    @NotNull
    @Pattern(regexp = "^[a-z0-9]*$")
    @Size(min = 1, max = 50)
    private String loginname;

    private String name;

    //1激活，0禁用
    private boolean activated = false;
    @JsonIgnore
    private String password;

    private String plainPassword;
    @JsonIgnore
    private String salt;

    @Email
    @Size(max = 100)
    private String email;

    public LocalDateTime registerDate;

//  private List<Role> roles;

    private String code;

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }


//  public List<Role> getRoles() {
//    return roles;
//  }
//
//  public void setRoles(List<Role> roles) {
//    this.roles = roles;
//  }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public boolean getActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", loginname='" + loginname + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", email='" + email + '\'' +
                ", registerDate=" + registerDate +
//                ", roles='" + roles + '\'' +
                "} " + super.toString();
    }

    public User() {
    }

    public User(long id, String loginname, String email) {
        this.id = id;
        this.loginname = loginname;
        this.email = email;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
