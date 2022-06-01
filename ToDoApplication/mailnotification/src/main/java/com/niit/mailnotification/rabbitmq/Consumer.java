package com.niit.mailnotification.rabbitmq;

import com.niit.mailnotification.model.EmailTemplate;
import com.niit.mailnotification.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues="user_queue")
    public void getEmailDTOFromRabbitMq(EmailDTO emailDTO){
        EmailTemplate emailTemplate=new EmailTemplate();
        emailTemplate.setEmail((emailDTO.getEmail()));
          emailService.sendTextEmail(emailTemplate);
    }
}
