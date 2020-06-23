package com.wqlm.boot.user.service;

import com.wqlm.boot.user.dto.InviteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;

@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendSimpleMail(InviteDTO dto){
        String content = "Moderator "+ dto.getModeratorAccount()+" invited you to join the Group "+dto.getGroupName()+ " http://localhost:63342/group12/index.html";
        SimpleMailMessage message =new SimpleMailMessage();
        System.out.println(content);
        System.out.println(from);
        System.out.println(dto.getWatcherAccount());
        message.setTo(dto.getWatcherAccount());
        message.setSubject("OOAD MOVIE GROUP GWU");
        message.setText(content);
        message.setFrom(from);
        mailSender.send(message);
        return true;
    }
}
