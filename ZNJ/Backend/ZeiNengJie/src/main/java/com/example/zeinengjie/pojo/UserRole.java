package com.example.zeinengjie.pojo;

import java.io.Serializable;

public class UserRole implements Serializable {
    private Long id;
    private Long userId;
    private Long roleId;

    // 构造方法
    public UserRole() {}

    public UserRole(Long id, Long userId, Long roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }

    // Getter 和 Setter 方法
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }
}
