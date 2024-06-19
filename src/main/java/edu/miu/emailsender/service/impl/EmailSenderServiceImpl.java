package edu.miu.emailsender.service.impl;

import edu.miu.emailsender.domain.EmailObject;
import edu.miu.emailsender.dto.EmailObjectDto;
import edu.miu.emailsender.service.EmailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author kush
 */
@Component
@Slf4j
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender emailSender;

    public EmailSenderServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public boolean sendSimpleMessage(EmailObject emailObject) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailObject.getFrom());
            message.setTo(emailObject.getTo());
            message.setSubject(emailObject.getSubject());
            message.setText(emailObject.getBody());
            emailSender.send(message);
            log.info("Email was sent to {} regarding {} at {} ",
                    emailObject.getTo(),emailObject.getSubject(), LocalDateTime.now());
            return true;
        }catch (Exception e){
            log.error("There was an issue sending email to {} regarding {} at {} ",
                    emailObject.getTo(),emailObject.getSubject(), LocalDateTime.now());
            return false;
        }
    }

    @Override
    public boolean sendSimpleMessage(EmailObjectDto emailObjectDto) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailObjectDto.getFrom());
            message.setTo(emailObjectDto.getTo().toArray(new String[0]));
            message.setCc(emailObjectDto.getCc().toArray(new String[0]));
            message.setBcc(emailObjectDto.getBcc().toArray(new String[0]));
            message.setSubject(emailObjectDto.getSubject());
            message.setText(emailObjectDto.getBody());
            emailSender.send(message);
            log.info("Email was sent to {} regarding {} at {} ",
                    emailObjectDto.getTo().toString(),emailObjectDto.getSubject(), LocalDateTime.now());
            return true;
        }
        catch (Exception e){
         log.error("There was an issue sending email to {} regarding {} at {} ",
                 emailObjectDto.getTo().toString(),emailObjectDto.getSubject(), LocalDateTime.now());
         return false;
        }
    }

}