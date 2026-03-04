package com.example.zeinengjie.service.imp;

import com.example.zeinengjie.Util.AliOsUtil;
import com.example.zeinengjie.Util.OssConfig;
import com.example.zeinengjie.Util.RedisUtil;
import com.example.zeinengjie.mapper.BackgroundManageMapper;
import com.example.zeinengjie.mapper.UserMapper;
import com.example.zeinengjie.pojo.Game;
import com.example.zeinengjie.pojo.GameAll;
import com.example.zeinengjie.pojo.GameImg;
import com.example.zeinengjie.pojo.User;
import com.example.zeinengjie.pojo.derive.*;
import com.example.zeinengjie.service.BackgroundManageService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.beans.Transient;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.*;

@Service
public class BackgroundManageImp implements BackgroundManageService {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BackgroundManageImp.class);

    @Autowired
    private LeaseImp leaseImp;
    @Autowired
    private BackgroundManageMapper backgroundManageMapper;
    @Autowired
    private ManagementImp managementImp;
    UUID uuid = UUID.randomUUID();
    @Autowired
    private UserImp userImp;
    @Resource
    private AliOsUtil aliOsUtil;
    @Autowired
    private OssConfig ossConfig;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public int insertGame(Game game, OrderPutaway form, List<MultipartFile> files) {
        String TitleAddContact = "[";
        //拼接再存到数据库里面格式为[{主标题,内容,内容,........},{主标题,'内容'，'内容'，.......}]
        logger.info("backgroundtest:" + "userName:" + form);
        for (Articles articles: form.getArticles()) {
            TitleAddContact+="{"+articles.getTitle()+","+articles.getContent()+"}"+",";
        }
        TitleAddContact+="]";
        logger.info("TitleAddContact:"+TitleAddContact);
        game.setGameName(form.getGame());
        game.setGameLv(form.getLevel());
        game.setGameCharacterName(form.getRank());
        game.setUname(form.getUname());
        game.setPwd(form.getPwd());
        game.setTitle(form.getTitledesc());
        game.setContent(TitleAddContact);
        User userId =getUserId(form.getUserName());
        game.setUserId(userId.getId().intValue());
        game.setGameStatus(0);
        game.setMoney(form.getPrice().doubleValue());
        String gameClass = getGameClass(form.getGame());
        game.setGameTag(gameClass);
        List<Game> gameInformation = getGameInformation(userId.getId().intValue());
        if(gameInformation.isEmpty()){
            backgroundManageMapper.insertGame(game);
        }else{
            for (Game g:gameInformation) {
                if(g.getGameName().equals(game.getGameName())&&g.getUname().equals(game.getUname())){
                    return 0;
                }
            }
            backgroundManageMapper.insertGame(game);
        }
        Game orderInformation = getOrderInformation(game.getGameName(), game.getUname());
        if (orderInformation!=null){
            logger.info("backgroundtest:"+"game:"+orderInformation.getId());
            if(orderInformation.getGameName().equals(game.getGameName())&&orderInformation.getUname().equals(game.getUname())){
                for (MultipartFile img : files) {
                    try {
                        String objectName = "gameImg/" + System.currentTimeMillis() + "_" + uuid.toString() + ".png";
                        aliOsUtil.uploadFile(img, objectName);
                        logger.info("文件上传成功: " + objectName);

                        GameImg gameImg = new GameImg();
                        gameImg.setImg("https://" + ossConfig.getBucketName() + "." + ossConfig.getEndpoint() + "/" + objectName);
                        gameImg.setGamesId(orderInformation.getId());
                        gameImg.setImgGameName(game.getGameName());
                        backgroundManageMapper.insertGameImg(gameImg);
                        logger.info("图片信息插入数据库成功");
                    } catch (Exception e) {
                        logger.error("文件上传或数据库插入失败", e);
                        return 0;
                    }
                }
                statisticsAddAuthorizationCommodity(game.getGameName());
                insertRentingOrder(orderInformation.getId(),  userId.getId().intValue(), orderInformation.getMoney());
                return 1;
            }
        }
        return 0;
    }
    public Double getUserMoney(String userName) {
        Integer userID=0;
        if(userName!=null){
            userID = backgroundManageMapper.getUserID(userName);
            logger.info("getUserMoney:"+userID);
            return backgroundManageMapper.getMoney(userID);
        }
        return 0.0;
    }

//获取上架的游戏账号和游戏名
    @Override
    public Game getOrderInformation(String gameName, String uname) {
        return backgroundManageMapper.getOrderInformation(gameName,uname);
    }

    @Override
    public int insertGameImg(GameImg gameImg) {
        return backgroundManageMapper.insertGameImg(gameImg);
    }

    @Override
    public List<Game> getGameInformation(Integer id) {
        return backgroundManageMapper.getGameInformation(id);
    }

    @Override
    public List<GameAll> getGameAll() {
        String Key = "game::"+"GameAll";
        if(redisUtil.hasKey(Key)){
            logger.info("redis:"+"game::"+"GameAll");
            return redisUtil.getList(Key);
        }else{
            List<GameAll> gameAll = backgroundManageMapper.getGameAll();
            logger.info("mysql:"+"game::"+"GameAll");
            String json = new Gson().toJson(gameAll);
            redisUtil.set(Key,json, 60*10);
            return gameAll;
        }
    }

    @Override
    public List<Game> getGameShow(String show) {
        return backgroundManageMapper.getGameShow(show);
    }

    @Override
    public List<GameShow> getGameShowT() {
        String key = "game::"+"GameShowT";
        if(redisUtil.hasKey(key)){
            logger.info("redis:"+"game::"+"GameShowT");
            return redisUtil.getList(key);
        }else{
            List<GameShow> gameShowT = backgroundManageMapper.getGameShowT();
            logger.info("mysql:"+"game::"+"GameShowT");
            String json = new Gson().toJson(gameShowT);
            redisUtil.set(key,json,60*10);
            return gameShowT;
        }
    }

    @Override
    public Integer getUserID(String uname) {
        return backgroundManageMapper.getUserID(uname);
    }

    @Override
    public Double getMoney(Integer userId) {
        return backgroundManageMapper.getMoney(userId);
    }

    @Override
    public List<UserRentingTable> getUserRentingTable(String userName, Integer page, Integer pageSize) {
        String key = "userRentingTable::"+userName+"::"+page+"::"+pageSize;
//        if(redisUtil.hasKey(key)){
//            logger.info("redis:"+"userRentingTable::"+userName+"::"+page+"::"+pageSize);
//            return redisUtil.getList(key);
//        }else{
            List<UserRentingTable> userRentingTable = backgroundManageMapper.getUserRentingTable(userName, page, pageSize);
            logger.info("mysql:"+"userRentingTable::"+userName+"::"+page+"::"+pageSize);
            String jons = new Gson().toJson(userRentingTable);
//            redisUtil.set(key,jons,60*10);
            return userRentingTable;
//        }
    }

    @Override
    public List<UserLeaseTable> getUserLeaseTable(String userName, Integer page, Integer pageSize) {
        return backgroundManageMapper.getUserLeaseTable(userName,page,pageSize);
    }

    @Override
    public Integer getUserRentingTableCount(String userName) {
        return backgroundManageMapper.getUserRentingTableCount(userName);
    }

    @Override
    public String getGameClass(String gameName) {
        return backgroundManageMapper.getGameClass(gameName);
    }

    @Override
    public Integer statisticsAddAuthorizationCommodity(String gameName) {
        logger.info("statisticsAddAuthorizationCommodity:"+gameName);
        return backgroundManageMapper.statisticsAddAuthorizationCommodity(gameName);
    }

    @Override
    public Integer shoppingAddOrder(Shopping order) {
        Integer userID = backgroundManageMapper.getUserID(order.getUser());
        GregorianCalendar gregorianCalendar = new GregorianCalendar();//获取时间工具类
        gregorianCalendar.setTime(new Date());//把当前时间放入gregorianCalendar中
        gregorianCalendar.add(Calendar.HOUR, order.getTime());//在当前时间上添加多少个小时
        System.out.println(new Timestamp(gregorianCalendar.getTime().getTime()));
        order.setLeaseorderStatus(0);
        Integer integer = backgroundManageMapper.shoppingAddOrder(order, new Timestamp(gregorianCalendar.getTime().getTime()), userID);
        //private Integer gamesStatus; 游戏状态 0：未上架/待审核 1：上架中 2：进行中 3：已结束
        if (integer == 1) {
            backgroundManageMapper.updateGameStatus(order.getOrderId(), 2);
            Game zuhaoItem = leaseImp.getZuhaoItem(order.getOrderId());
            backgroundManageMapper.upGameAllStatus3(zuhaoItem.getGameName());//订单进行后known的数量+1
            managementImp.UpGameAllLeisureJian(zuhaoItem.getGameName());////订单进行后leisure的数量-1
        }
        return integer;
    }

    @Override
    public Integer updateGameStatus(Integer orderId, Integer gameStatus) {

        return backgroundManageMapper.updateGameStatus(orderId, gameStatus);
    }

    @Override
    public Integer getUserLeaseTableCount(String userName) {
        return backgroundManageMapper.getUserLeaseTableCount(userName);
    }

    @Override
    public Integer upGameAllStatus3(String gameName) {
        return backgroundManageMapper.upGameAllStatus3(gameName);
    }

    @Override
    public Integer insertRentingOrder(Integer gameId, Integer userId, double money) {
        return backgroundManageMapper.insertRentingOrder(gameId,userId,money);
    }

    public User getUserId(String userName){ //获取用户id
        return userMapper.getUserStatus(userName);
    }

    @Override
    public Integer getRentalIncome(Integer userId) {
        return backgroundManageMapper.getRentalIncome(userId);
    }

    @Override
    public Integer getRentalOrder(Integer userId) {
        return backgroundManageMapper.getRentalOrder(userId);
    }

    @Override
    public Integer LeasingExpenditure(Integer userId) {
        return backgroundManageMapper.LeasingExpenditure(userId);
    }

    @Override
    public Integer LeasingOrder(Integer userId) {
        return backgroundManageMapper.LeasingOrder(userId);
    }

    @Override
    public Integer upOrderStatus(String UserName, Integer orderId, Integer status,String gameName) {
        User user = userImp.getUserStatus(UserName);
        managementImp.upGameAllStatus2(gameName);//空闲订单leisure加一
        return backgroundManageMapper.upOrderStatus(user.getId().intValue(),orderId,status);
    }

    @Override
    public Integer UserDeleteOrder(String UserName, Integer orderId, Integer status,String gameName) {
        User user = userImp.getUserStatus(UserName);
        managementImp.UpGameAllLeisureJian(gameName);//订单删除后leisure数量减一
        return backgroundManageMapper.UserDeleteOrder(user.getId().intValue(),orderId,status);
    }
}
