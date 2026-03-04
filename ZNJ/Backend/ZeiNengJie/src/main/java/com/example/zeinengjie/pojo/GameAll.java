package com.example.zeinengjie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@SuppressWarnings({"all"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameAll implements Serializable {
    private Integer gameallId;
    private String gameallName;
    private String gameallClass;
    private String os;
    private Integer known;
    private Integer leisure;
    private Integer authorization;
    private Integer endOrderNub;

}
