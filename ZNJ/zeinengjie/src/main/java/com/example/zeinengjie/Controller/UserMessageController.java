package com.example.zeinengjie.Controller;

import com.example.zeinengjie.Util.*;
import com.example.zeinengjie.pojo.User;
import com.example.zeinengjie.service.imp.BackgroundManageImp;
import com.example.zeinengjie.service.imp.UserImp;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/")
public class UserMessageController {
    Logger logger = org.slf4j.LoggerFactory.getLogger(UserMessageController.class);
    @Autowired
    private UserImp userImp;
    UUID uuid = UUID.randomUUID();
    @Autowired
    private BackgroundManageImp backgroundManageImp;
    @Autowired
    private JwtUtil jwtUtil;
    @Resource
    private AliOsUtil aliOsUtil;
    @Autowired
    private OssConfig ossConfig;
    @Autowired
    private Mail setMail;
    @Autowired
    private RedisUtil redisUtil;
    @PostMapping("login")
    public Map<String, Object> test_fun(@RequestBody Map<String,String> param){
        String userName = param.get("username");
        String userPassword = param.get("password");
        User user = userImp.getUserByName(userName, userPassword);
        Map<String,Object> requestMap = new HashMap<String,Object>();
        if(user!=null){
            if(user.getStatus()==2){
                requestMap.put("message","账号被禁用");
                return requestMap;
            }
            logger.info("登录成功");
            String toKen = jwtUtil.generateToken(userName);
            requestMap.put("token",toKen);
            requestMap.put("HData",user);
            requestMap.put("message","登录成功");
            return requestMap;
        }else{
            logger.info("登录失败");
            requestMap.put("message","登录失败"+userName+":"+userPassword);
        }
        return requestMap;
    }

    @GetMapping("zuhaoh")
//    @RequiresRoles("admin")
    public Tool test_fun2(){
        Tool tool = new Tool();
        tool.setData(userImp.getUserByNames());
        return tool;
    }

    @GetMapping("unauthorized")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unauthorized() {
        logger.info("unauthorized");
        return "unauthorized";
    }

    @PostMapping("uploadimg")
    public Tool uploadimg(@RequestParam("file") MultipartFile file,
                          @RequestParam("userName") String userName){
        Tool tool = new Tool();
        Map<String,String> requestMap =new HashMap();
        logger.info("fileName"+file.getName()+"userName:"+userName);
        if(!file.isEmpty()){
            String objectName = "uploads/" + System.currentTimeMillis() + "_" + uuid.toString()+".png";
            aliOsUtil.uploadFile(file,objectName);
            requestMap.put("img_url","https://" + ossConfig.getBucketName()+"."+ossConfig.getEndpoint()+ "/" + objectName);
            userImp.updateUser(userName,"https://" + ossConfig.getBucketName()+"."+ossConfig.getEndpoint()+ "/" + objectName);
            logger.info("fileName2"+file.getOriginalFilename());
            tool.setData(requestMap);
            tool.setMessage("上传成功");
            tool.setStatusCode(200);
        }else {
            tool.setStatusCode(404);
            tool.setMessage("上传失败");
        }
        return tool;
    }

    @PostMapping("register")
    public Tool sign(@RequestBody User user){
        Tool tool = new Tool();
        logger.info("register:"+user.toString());
        if(user!=null){//判断用户名是否存在
        String key = "mailCode::"+user.getCode();//设置键
            logger.info("redis:"+redisUtil.get(key));
            logger.info("redis:"+key);
        if(redisUtil.hasKey(key)){//判断redis中是否有键值对
            String code = redisUtil.get(key);//获取redis中的值
            if(code.equals(user.getCode())){//判断redis中的值是否与用户输入的值一致
                logger.info("注册成功:"+code);
                userImp.register(user);
                Integer userID = backgroundManageImp.getUserID(user.getUsername());
                userImp.insertMoney(userID);
                tool.setStatusCode(200);
                tool.setMessage("注册成功");
            }
        }
        }else {
            tool.setStatusCode(404);
            tool.setMessage("注册失败");
        }
        return tool;
    }
    @PostMapping("getmail")
    public Tool getmail(@RequestBody Map<String,String> mail){
        logger.info("getmail:"+mail);
        Map<String,String> requestMap = new HashMap();
        String IsMail = mail.get("mail");
        Tool tool = new Tool();
        if(IsMail.contains("@")){
            try {
                tool.setStatusCode(200);
                tool.setMessage("验证码发送成功");
                String mail1Code = setMail.getMail(IsMail);
                requestMap.put("code",mail1Code);
                String key = "mailCode::"+mail1Code;
                redisUtil.set(key,mail1Code,60*5);
                logger.info("redis:"+redisUtil.get(key));
                logger.info("redis:"+key);
                tool.setData(requestMap);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }else{
            tool.setStatusCode(404);
            tool.setMessage("验证码发送失败");
        }
        return tool;
    }
    @PostMapping("getuseravatar")
    public String getuseravatar(@RequestBody Map<String,String> userName){
        String user_name = userName.get("userName");
        String token = userName.get("token");
        logger.info("getuseravatar:"+userImp.getAvatar(user_name));
        Tool tool = new Tool();
        if(!jwtUtil.validateToken(token)){
            tool.setStatusCode(401);
            tool.setMessage("token验证失败");
            return tool.getStatusCode().toString();
        }
        if(!userName.isEmpty()){
            tool.setData(userImp.getAvatar(user_name));
            tool.setStatusCode(200);
            tool.setMessage("头像获取成功");
        }else{
            tool.setStatusCode(401);
            tool.setMessage("头像获取失败");
        }
        String data = tool.getData()+"";
        if(data.isEmpty()){
            data="https://img.alicdn.com/tfs/TB1.YXh3MHqK1RjSZFkXXX.WFXa-1024-1024.png";
        }
        return data;
    }
}
