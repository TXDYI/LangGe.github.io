package com.example.zeinengjie.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoneyAll implements Serializable {
    private Integer moneyall;
    private Long usersId;
    private  Double usersMoney;
}
