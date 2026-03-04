package com.example.zeinengjie.pojo.derive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameZUhaoOrder implements Serializable {
    private Integer id;
    private String gameName;
    private String title;
    private Double money;
    private String content;
    private String gameCharacterName;
    private String gamelv;
    private List<String> img;

}
