package com.example.zeinengjie.service;

import com.example.zeinengjie.pojo.Game;
import com.example.zeinengjie.pojo.GameImg;
import com.example.zeinengjie.pojo.derive.GameZUhaoOrder;

import java.util.List;

public interface LeaseService{
    Game getZuhaoItem(Integer id);
    List<GameImg> getGameImg(Integer id);

}
