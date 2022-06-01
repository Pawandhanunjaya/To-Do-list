package com.niit.mailnotification.service;

import com.niit.mailnotification.model.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;




    public void sendTextEmail(EmailTemplate emailTemplate) {

        SimpleMailMessage msg = new SimpleMailMessage();
        try {


                msg.setTo(emailTemplate.getEmail());
                msg.setSubject("Registered");
                msg.setText("Sucessfully registered");
                javaMailSender.send(msg);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


}

