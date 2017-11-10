package com.br.lp2.model.javabeans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Camila
 */
public class Userinfo implements Serializable{
    private long id_userinfo;
    private String firstname, lastname, email, avatar;
    private Date birthday;

    public Userinfo() {
    }

    public long getId_userinfo() {
        return id_userinfo;
    }

    public void setId_userinfo(long id_userinfo) {
        this.id_userinfo = id_userinfo;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Userinfo{" + "id_userinfo=" + id_userinfo + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", avatar=" + avatar + ", birthday=" + birthday + '}';
    }
    
}
