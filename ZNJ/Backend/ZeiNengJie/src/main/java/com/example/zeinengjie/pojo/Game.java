package com.example.zeinengjie.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Game implements Serializable {
    private Integer id;
    private String gameName;
    private String gameCharacterName;
    private String gameTag;
    private String gameLv;
    private String title;
    private String content;
    private Integer username;
    private Integer gamesStatus; //游戏状态 0：未上架/待审核 1：上架中 2：进行中 3：已结束 4：重新上架
    private Double money;
    private String uname;
    private String pwd;
    private String gameShow;
    private Timestamp orderTime;

    public Game(Integer id, String gameName, String gameCharacterName, String gameTag, String gameLv, String title, String content, Integer username, Integer gameStatus, Double money, String uname, String pwd, String gameShow,Timestamp orderTime) {
        this.id = id;
        this.gameName = gameName;
        this.gameCharacterName = gameCharacterName;
        this.gameTag = gameTag;
        this.gameLv = gameLv;
        this.title = title;
        this.content = content;
        this.username = username;
        this.gamesStatus = gameStatus;
        this.money = money;
        this.uname = uname;
        this.pwd = pwd;
        this.gameShow = gameShow;
        this.orderTime = orderTime;
    }

    // 构造方法
    public Game() {}

    // Getter 和 Setter 方法
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getGameName() { return gameName; }
    public void setGameName(String gameName) { this.gameName = gameName; }

    public String getGameCharacterName() { return gameCharacterName; }
    public void setGameCharacterName(String gameCharacterName) { this.gameCharacterName = gameCharacterName; }

    public String getGameTag() { return gameTag; }
    public void setGameTag(String gameTag) { this.gameTag = gameTag; }

    public String getGameLv() {
        return gameLv;
    }

    public void setGameLv(String gameLv) {
        this.gameLv = gameLv;
    }

    public Integer getUserId() {
        return username;
    }

    public void setUserId(Integer userId) {
        this.username = userId;
    }

    public Integer getGameStatus() {
        return gamesStatus;
    }

    public void setGameStatus(Integer gameStatus) {
        this.gamesStatus = gameStatus;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getGameShow() {
        return gameShow;
    }

    public void setGameShow(String gameShow) {
        this.gameShow = gameShow;
    }

    public Integer getGamesStatus() {
        return gamesStatus;
    }

    public void setGamesStatus(Integer gamesStatus) {
        this.gamesStatus = gamesStatus;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                ", gameCharacterName='" + gameCharacterName + '\'' +
                ", gameTag='" + gameTag + '\'' +
                ", gameLv='" + gameLv + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", username=" + username +
                ", gamesStatus=" + gamesStatus +
                ", money=" + money +
                ", uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", gameShow='" + gameShow + '\'' +
                ", orderTime=" + orderTime +
                '}';
    }
}

