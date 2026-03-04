package com.example.zeinengjie.pojo.derive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpUserStatus implements Serializable {
    private Integer selectedRoleId;
    private String selectedRoleName;
    private Integer orderId;
    private String remark;
    private Boolean status;
}
