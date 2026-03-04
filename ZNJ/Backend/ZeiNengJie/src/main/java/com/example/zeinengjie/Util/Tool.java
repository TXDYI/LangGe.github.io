package com.example.zeinengjie.Util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Tool {
    public static Integer statusCode = 0;
    public static String message = null;
    public static Object data = null;
    public void setStatusCode(Integer statusCode){
        this.statusCode = statusCode;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public void setData(Object data){
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
    public  String getMessage() {
        return message;
    }
    public  Object getData() {
        return data;
    }
    public void ToolInit(){
        this.statusCode = 0;
        this.message = null;
        this.data = null;
    }
}
