package com.example.zeinengjie.pojo.derive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
@SuppressWarnings({"all"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListItem implements Serializable {
    private Integer id;
    private String username;
    private Integer status;
    private Timestamp createTime;
    private String phone;
    private String mail;
    private String Userdescription;
}
