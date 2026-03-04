package com.example.zeinengjie.pojo.derive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings({"all"})
public class Shopping implements Serializable {
    private int orderId;
    private Double money;
    private Integer time;
    private String user;
    private Integer leaseorderStatus;
}
