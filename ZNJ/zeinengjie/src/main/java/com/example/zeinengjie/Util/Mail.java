package com.example.zeinengjie.Util;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import java.security.GeneralSecurityException;
import java.util.Properties;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Component
public class Mail {
    @Value("${mail.host}")
    private String host = "smtp.qq.com";
    @Value("${mail.transport.protocol}")
    private String protocol = "smtp";
    @Value("${mail.smtp.auth}")
    private String auth = "true";
    @Value("${mail.smtp.ssl.enable}")
    private String sslEnable = "true";
    @Value("${mail.smtp.ssl.protocols}")
    private String sslProtocols="TLSv1.2";
    @Value("${qq.qq-id}")
    private String qqId="xxxxxxxxx";//邮箱
    @Value("${qq.qq-password}")
    private String qqPassword="xxxxxxxx";//授权码
    public String getMail(String to) throws GeneralSecurityException, MessagingException {
        Properties proper = new Properties();
        proper.setProperty("mail.host","smtp.qq.com");//设置QQ邮箱服务器
        proper.setProperty("mail.transport.protocol","smtp");//邮箱发送协议
        proper.setProperty("mail.smtp.auth","true");//需要验证用户密码

        //关于QQ邮箱，还要设置ssl加密，了解可百度
        MailSSLSocketFactory SSL = new MailSSLSocketFactory();
        SSL.setTrustAllHosts(true);
        proper.put("mail.smtp.ssl.enable","true");
        proper.put("mail.smtp.socketFactory",SSL);
        proper.put("mail.smtp.ssl.protocols", "TLSv1.2");
        //QQ才有，其它没有
        Session s = Session.getDefaultInstance(proper, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //发件人，和授权码
                return new PasswordAuthentication(qqId,qqPassword);
            }
        });
        s.setDebug(true);
        //通过session拿到Transport（运输）连接
        Transport ts = s.getTransport();
        //使用邮箱的用户名和授权码连上服务器
        ts.connect(host,qqId,qqPassword);
        //创建邮件
        //传递一个session，不然你怎么知道消息是从哪里来的
        MimeMessage message = new MimeMessage(s);
        //指明发件人
        Address address = new InternetAddress(qqId);
        message.setFrom(address);
        //指明收件人
        address = new InternetAddress(to);
        message.setRecipient(Message.RecipientType.TO, address);
        //设置主题
        message.setSubject("欢迎注册!贼能借");
        //这也是一块区域用来放正文
        MimeBodyPart text = new MimeBodyPart();
        //cid是ContentID("tx.jpg")的缩写，通过设置内容的id来定位
        //生成六位数字验证码
        String s1 = VerificationCodeGenerator.generateSixDigitCode();
        String strText="欢迎使用贼能借，借号平台感谢你的使用<br>这是你的授权码:<span style='color:red;'>"+s1+"</span>";
        text.setContent(strText,"text/html;charset=UTF-8");
        //描述关系，把这些内容添加到一大的区域放在一起，有点像css里面的盒子，mimeMultipart.setSubType("related");一共有三种大区域，详细请百度
        MimeMultipart mimeMultipart = new MimeMultipart();
        mimeMultipart.addBodyPart(text);
        mimeMultipart.setSubType("related");
        //设置到消息中，保存修改
        message.setContent(mimeMultipart);//把编辑好的邮件放到消息里面
        message.saveChanges();//保存修改
        //发送邮件
        ts.sendMessage(message,message.getAllRecipients());
        //关闭邮件
        ts.close();
        return s1;
    }
    public void UpPassword(String to,String GameName,Integer orderId,String uname) throws GeneralSecurityException, MessagingException {
        Properties proper = new Properties();
        proper.setProperty("mail.host",host);//设置QQ邮箱服务器
        proper.setProperty("mail.transport.protocol",protocol);//邮箱发送协议
        proper.setProperty("mail.smtp.auth",auth);//需要验证用户密码\

        //关于QQ邮箱，还要设置ssl加密，了解可百度
        MailSSLSocketFactory SSL = new MailSSLSocketFactory();
        SSL.setTrustAllHosts(true);
        proper.put("mail.smtp.ssl.enable",sslEnable);
        proper.put("mail.smtp.socketFactory",SSL);
        proper.put("mail.smtp.ssl.protocols", sslProtocols);
        //QQ才有，其它没有
        Session s = Session.getDefaultInstance(proper, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //发件人，和授权码
                return new PasswordAuthentication(qqId,qqPassword);
            }
        });
        s.setDebug(true);
        //通过session拿到Transport（运输）连接
        Transport ts = s.getTransport();
        //使用邮箱的用户名和授权码连上服务器
        ts.connect(host,qqId,qqPassword);
        //创建邮件
        //传递一个session，不然你怎么知道消息是从哪里来的
        MimeMessage message = new MimeMessage(s);
        //指明发件人
        Address address = new InternetAddress(qqId);
        message.setFrom(address);
        //指明收件人
        address = new InternetAddress(to);
        message.setRecipient(Message.RecipientType.TO, address);
        //设置主题
        message.setSubject("欢迎注册!贼能借");
        //这也是一块区域用来放正文
        MimeBodyPart text = new MimeBodyPart();
        //cid是ContentID("tx.jpg")的缩写，通过设置内容的id来定位
        StringBuilder sb = new StringBuilder();
        sb.append("欢迎使用贼能借，您被租赁的游戏账号到期了，请及时修改密码<br>");
        sb.append("订单号:<span style='color:red;'>").append(orderId).append("</span><br>");
        sb.append("游戏名：<span style='color:red;'>").append(GameName).append("</span><br>");
        sb.append("游戏账号：<span style='color:red;'>").append(uname).append("</span>");

        text.setContent(sb.toString(), "text/html;charset=UTF-8");
        //描述关系，把这些内容添加到一大的区域放在一起，有点像css里面的盒子，mimeMultipart.setSubType("related");一共有三种大区域，详细请百度
        MimeMultipart mimeMultipart = new MimeMultipart();
        mimeMultipart.addBodyPart(text);
        mimeMultipart.setSubType("related");
        //设置到消息中，保存修改
        message.setContent(mimeMultipart);//把编辑好的邮件放到消息里面
        message.saveChanges();//保存修改
        //发送邮件
        ts.sendMessage(message,message.getAllRecipients());
        //关闭邮件
        ts.close();
    }
}
