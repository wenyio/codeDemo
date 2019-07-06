package com.iscolt.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;


    @Test
    public void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();//简单邮件
        //邮件设置
        message.setSubject("通知>>今晚开房");
        message.setText("今晚11点,享受papapa>>part!");

        message.setTo("1250074822@qq.com");
        message.setFrom("1329208516@qq.com");
        mailSender.send(message);
        System.out.println("成功");
    }

    @Test
    public void test02() throws Exception{
        //创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //邮件设置
        helper.setSubject("通知>>今晚开房");
        helper.setText("<b style='color:red'>今晚11点,享受papapa>>part!</b>",true);//开启html格式

        helper.setTo("1250074822@qq.com");
        helper.setFrom("1329208516@qq.com");

        //上传文件
        helper.addAttachment("1.jpg",new File("F:\\图片\\picture\\1.jpg"));
        helper.addAttachment("2.jpg",new File("F:\\图片\\picture\\2.jpg"));
        mailSender.send(mimeMessage);
        System.out.println("成功");
    }

}
