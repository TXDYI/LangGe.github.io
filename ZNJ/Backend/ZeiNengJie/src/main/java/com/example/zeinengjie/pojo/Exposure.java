package com.example.zeinengjie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exposure implements Serializable {
    private Integer idExposure;
    private String titleText;
    private String explainText;
    private String exposureImg;
    private char exposureShow;
}
