package com.example.zeinengjie.pojo.derive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderManageAdmin implements Serializable {
    private Integer id;
    private String gameName;
    private String gameCharacterName;
    private String gameTag;
    private Double money;
    private Timestamp orderTime;
    private String username;
    private Integer gameStatus;
}
