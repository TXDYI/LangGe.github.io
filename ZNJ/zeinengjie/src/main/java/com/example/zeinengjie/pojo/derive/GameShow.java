package com.example.zeinengjie.pojo.derive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameShow {
    private Integer id;
    private String gameName;
    private String title;
    private String img;
    private Double money;
}
