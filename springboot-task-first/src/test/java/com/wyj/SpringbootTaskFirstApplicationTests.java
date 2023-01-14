package com.wyj;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class SpringbootTaskFirstApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    // 发送简单邮件
    @Test
    void contextLoads() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setSubject("狂神，你好");
        mailMessage.setText("谢谢你的狂神说Java系列课程");

        mailMessage.setTo("1637317579@qq.com");
        mailMessage.setFrom("1013801973@qq.com");
        javaMailSender.send(mailMessage);
    }

    // 发送复杂邮件
    @Test
    void contextLoads2() throws MessagingException {
        // 创建复杂邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // 组装 true-表示是否支持多消息发送, 文本、附件、内联元素等...
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //正文 true-表示是否支持 html 内容文本发送
        helper.setSubject("狂神，你好~plus");
        helper.setText("<p style='color:red'>谢谢你的狂神说Java系列课程</p>", true);

        //附件
        helper.addAttachment("1.jpg", new File("D:\\Program Files\\workspace\\idea\\1.jpg"));
        helper.addAttachment("2.jpg", new File("D:\\Program Files\\workspace\\idea\\2.jpg"));

        helper.setTo("1637317579@qq.com");
        helper.setFrom("1013801973@qq.com");

        javaMailSender.send(mimeMessage);


    }



}
