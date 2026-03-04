package com.example.zeinengjie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
@SuppressWarnings({"all"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaseOrder implements Serializable {
    private Integer leaseOrderId;
    private Integer usersId;
    private Integer leaseorderStatus;
    private Integer gamesId;
    private Timestamp createTime;
    private Double leaseorderMoney;
    private Timestamp endTime;
}
