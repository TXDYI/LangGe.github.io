package com.example.zeinengjie.pojo.derive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings({"all"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLeaseTable implements Serializable {
    private Integer id;
    private String gameName;
    private Timestamp createTime;
    private Double leaseorderMoney;
    private Integer leaseorderStatus;//0租赁中，1租赁结束
    private Integer gamesStatus;
    private Timestamp endTime;

}
