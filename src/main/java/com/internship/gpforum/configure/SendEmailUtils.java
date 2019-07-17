package com.internship.gpforum.configure;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

public class SendEmailUtils {
//    private static Logger logger = Logger.getLogger("SendEmailUtils.class");

    //用户名密码验证，需要实现抽象类Authenticator的抽象方法PasswordAuthentication
    static class MyAuthenricator extends Authenticator {
        String u = null;
        String p = null;

        public MyAuthenricator(String u, String p) {
            this.u = u;
            this.p = p;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(u, p);
        }
    }

    public static void send(String title, String context, String sendEmail) throws Exception {
        Properties prop = new Properties();
        //协议
        prop.setProperty("mail.transport.protocol", "smtps");
        //服务器
        prop.setProperty("mail.smtp.host", "smtp.exmail.qq.com");
        //端口
        prop.setProperty("mail.smtp.port", "465");
        //使用smtp身份验证
        prop.setProperty("mail.smtp.auth", "true");
        //使用SSL，企业邮箱必需！
        //开启安全协议
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        }
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        prop.put("mail.smtp.socketFactory.fallback", "false");
        //发件人，进行权限认证
        Session session = Session.getDefaultInstance(prop, new MyAuthenricator("631607040318@mails.cqjtu.edu.cn", "zrR9CScw5B89hfnG"));
//        session.setDebug(true);
        MimeMessage mimeMessage = new MimeMessage(session);
        //发件人地址
        mimeMessage.setFrom(new InternetAddress("631607040318@mails.cqjtu.edu.cn", ""));
        //收件人的地址
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(sendEmail));
        mimeMessage.setSubject(title);
        mimeMessage.setSentDate(new Date());
        mimeMessage.setText(context);
        mimeMessage.saveChanges();
        Transport.send(mimeMessage);
    }
}
