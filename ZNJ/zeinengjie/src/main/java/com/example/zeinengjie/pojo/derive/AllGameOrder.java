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
public class AllGameOrder implements Serializable {
    private Integer id;
    private String gameName;
    private Timestamp orderTime;
    private String username;
    private Integer status;
}
