package com.example.zeinengjie.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Role implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Timestamp createTime;

    // 构造方法
    public Role() {}

    public Role(Long id, String name, String description, Timestamp createTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createTime = createTime;
    }

    // Getter 和 Setter 方法
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Timestamp getCreateTime() { return createTime; }
    public void setCreateTime(Timestamp createTime) { this.createTime = createTime; }
}
