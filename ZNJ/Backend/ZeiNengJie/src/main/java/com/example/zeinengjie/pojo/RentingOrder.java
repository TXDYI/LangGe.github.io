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
public class RentingOrder implements Serializable {
    private  Integer rentingOrder;
    private  Integer gamesId;
    private  Integer usersId;
    private Timestamp createTime;
    private  Double rentingorderMoney;
}
