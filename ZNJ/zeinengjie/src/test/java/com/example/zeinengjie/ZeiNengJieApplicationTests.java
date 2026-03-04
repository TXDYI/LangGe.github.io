package com.example.zeinengjie;

import com.example.zeinengjie.Util.AliOsUtil;
import com.example.zeinengjie.Util.Mail;
import com.example.zeinengjie.Util.VerificationCodeGenerator;
import com.example.zeinengjie.mapper.BackgroundManageMapper;
import com.example.zeinengjie.mapper.BargainMapper;
import com.example.zeinengjie.mapper.ManagementMapper;
import com.example.zeinengjie.mapper.TestMapper;
import com.example.zeinengjie.pojo.*;
import com.example.zeinengjie.pojo.derive.*;
import com.example.zeinengjie.service.imp.BackgroundManageImp;
import com.example.zeinengjie.service.imp.HomeManagementImp;
import com.example.zeinengjie.service.imp.LeaseImp;
import com.example.zeinengjie.service.imp.ManagementImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import javax.sql.DataSource;
import java.security.GeneralSecurityException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@SpringBootTest
class ZeiNengJieApplicationTests {
@Autowired
private HomeManagementImp homeManagementImp;
@Autowired
private AliOsUtil aliOsUtil;
@Autowired
private TestMapper testMapper;
@Autowired
private DataSource dataSource;
@Autowired
private LeaseImp leaseImp;

@Autowired
private BargainMapper bargainMapper;
@Autowired
private BackgroundManageImp backgroundManageImp;
@Autowired
private BackgroundManageMapper backgroundManageMapper;
@Autowired
private ManagementImp managementImp;
@Autowired
private ManagementMapper managementMapper;
//    @Test
//    void contextLoads() {
//        List<Exposure> exposure = homeManagementImp.getExposure();
//        for (Exposure e: exposure) {
//            System.out.println(e);
//        }
//    }
//    @Test
//    void str() {
//        List<GameImg> gameImg = testMapper.getGameImg();//oss清空订单图片
//        for (GameImg gameImg1: gameImg) {
//            aliOsUtil.removeFile(gameImg1.getImg());
//        } }

//    @Test
//    void testDruid() throws Exception {
//        System.out.println("数据源类型：" + dataSource.getClass().getName());
//        System.out.println("连接：" + dataSource.getConnection());
//    }
//    @Test
//    void test() {
//        GameZUhaoOrder zuhaoOrder = leaseImp.getZuhaoOrder(1);
//    }
//
//    @Test
//    void test09(){
//        Integer bargain = bargainMapper.getBargain("CS:GO2");
//        System.out.println(bargain);
//    }
//    @Test
//    void test08(){
//        List<Bargain> cyhx = bargainMapper.getBargains(0, "CS:GO2");
//        for (Bargain b: cyhx) {
//            System.out.println(b.toString());
//        }
//    }
    @Test
    void test07(){
        Double admin = backgroundManageImp.getUserMoney("abcde");
        System.out.println(admin);
    }
//    @Test
//    void test06(){
//        List<UserRentingTable> admin = backgroundManageImp.getUserRentingTable("admin", 0, 10);
//        for (UserRentingTable u:admin) {
//            System.out.println(u.toString());
//        }
//    }
//    @Test
//    void test05(){
//        Game admin = backgroundManageImp.getOrderInformation("穿越火线", "1234567890");
//        Timestamp orderTime = admin.getOrderTime();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        GregorianCalendar gregorianCalendar = new GregorianCalendar();
//        gregorianCalendar.setTime(new Date());
//        gregorianCalendar.add(Calendar.HOUR, 1);
//        System.out.println(simpleDateFormat.format(gregorianCalendar.getTime()));
//    }
    @Test
    void test04(){
        Shopping shopping = new Shopping(1,6.0,-1,"admin",0);
        Integer userID = backgroundManageImp.getUserID(shopping.getUser());
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.add(Calendar.HOUR, shopping.getTime());
        System.out.println(new Timestamp(gregorianCalendar.getTime().getTime()));
        backgroundManageMapper.shoppingAddOrder(shopping,new Timestamp(gregorianCalendar.getTime().getTime()),userID);
    }
//    @Test
//    void test03(){
//        Integer admin = backgroundManageImp.getUserLeaseTableCount("admin");
//        System.out.println(admin);
//    }
//
//    @Test
//    void test02(){
//        System.out.println(new VerificationCodeGenerator().generateSixDigitCode());
//    }
//

//    @Test
//    void test01(){
//        Integer allOrderAdminPageCount = managementMapper.getAllOrderAdminPageCount(2, "");
//        System.out.println(allOrderAdminPageCount);
//    }

//    @Test
//    void test01(){
//        List<GameAll> gameAll = managementMapper.getGameAll("");
//        for (GameAll g: gameAll) {
//            System.out.println(g.toString());
//        }
//    }
    @Test
    void test01(){
        try {
            new Mail().UpPassword("2045272344@qq.com","穿越火线",1,"1234567890");
//            new Mail().getMail("2045272344@qq.com");
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
