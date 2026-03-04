package com.example.zeinengjie.pojo.derive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bargain implements Serializable {
    private Integer id;
    private String gameName;
    private String title;
    private String img;
    private Double money;
}
