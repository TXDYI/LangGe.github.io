package com.example.zeinengjie.pojo.derive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRentingTable implements Serializable {
    private Integer id;
    private String gameName;
    private Timestamp orderTime;
    private Double money;
    private Integer gamesStatus;
}
