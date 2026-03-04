package com.example.zeinengjie.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameImg {
    private Integer id;
    private String imgGameName;
    private String img;
    private Integer gamesId;
}
