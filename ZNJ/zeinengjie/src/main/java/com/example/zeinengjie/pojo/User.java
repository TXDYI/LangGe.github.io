package com.example.zeinengjie.pojo;

import java.io.Serializable;
import java.sql.Timestamp;


@SuppressWarnings({"all"})
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String Userdescription;
    private Integer status;
    private Timestamp createTime;
    private String mail;
    private String phone;
    private String avatar;
    private String code;

    // 构造方法
    public User() {}

    public User(Long id, String username, String password, String userdescription, Integer status, Timestamp createTime, String mail, String phone, String avatar, String code) {
        this.id = id;
        this.username = username;
        this.password = password;
        Userdescription = userdescription;
        this.status = status;
        this.createTime = createTime;
        this.mail = mail;
        this.phone = phone;
        this.avatar = avatar;
        this.code = code;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", Userdescription='" + Userdescription + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    // Getter 和 Setter 方法

    public String getUserdescription() {
        return Userdescription;
    }

    public void setUserdescription(String userdescription) {
        Userdescription = userdescription;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getSalt() { return Userdescription; }
    public void setSalt(String salt) { this.Userdescription = salt; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public Timestamp getCreateTime() { return createTime; }
    public void setCreateTime(Timestamp createTime) { this.createTime = createTime; }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
