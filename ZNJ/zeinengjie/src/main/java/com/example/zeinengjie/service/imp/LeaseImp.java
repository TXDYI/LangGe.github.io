package com.example.zeinengjie.service.imp;

import com.example.zeinengjie.mapper.LeaseMapper;
import com.example.zeinengjie.pojo.Game;
import com.example.zeinengjie.pojo.GameImg;
import com.example.zeinengjie.pojo.derive.GameZUhaoOrder;
import com.example.zeinengjie.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Service
public class LeaseImp implements LeaseService {
    private Logger logger = Logger.getLogger(LeaseImp.class.getName());
    @Autowired
    private LeaseMapper leaseMapper;
    @Override
    public Game getZuhaoItem(Integer id) {
        return leaseMapper.getZuhaoItem(id);
    }
    @Override
    public List<GameImg> getGameImg(Integer id) {
        return leaseMapper.getGameImg(id);
    }
    public GameZUhaoOrder getZuhaoOrder(Integer id) {
        GameZUhaoOrder gameZUhaoOrder = new GameZUhaoOrder();
        Game game = leaseMapper.getZuhaoItem(id);
        List<String> imgs =new ArrayList<>();
        List<GameImg> gameImg = leaseMapper.getGameImg(id);
        for (GameImg img: gameImg) {
            imgs.add(img.getImg());
        }
        gameZUhaoOrder.setImg(imgs);
        gameZUhaoOrder.setGameName(game.getGameName());
        gameZUhaoOrder.setId(game.getId());
        gameZUhaoOrder.setContent(game.getContent());
        gameZUhaoOrder.setTitle(game.getTitle());
        gameZUhaoOrder.setMoney(game.getMoney());
        gameZUhaoOrder.setGamelv(game.getGameLv());
        gameZUhaoOrder.setGameCharacterName(game.getGameCharacterName());
        logger.info("gameZUhaoOrder:"+gameZUhaoOrder);
        return gameZUhaoOrder;
    }
}
