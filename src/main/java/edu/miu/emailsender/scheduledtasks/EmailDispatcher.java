package edu.miu.emailsender.scheduledtasks;

import edu.miu.emailsender.domain.EmailObject;
import edu.miu.emailsender.service.EmailObjectService;
import edu.miu.emailsender.service.EmailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author kush
 */

@Component
@Slf4j
public class EmailDispatcher {

    final
    EmailObjectService emailObjectService;

    final
    EmailSenderService emailSenderService;

    @Value("${app.email.retry.since-hrs}")
    private int retrySinceHrs;

    public EmailDispatcher(EmailObjectService emailObjectService, EmailSenderService emailSenderService) {
        this.emailObjectService = emailObjectService;
        this.emailSenderService = emailSenderService;
    }

    @Scheduled(initialDelay = 10L,fixedDelayString = "#{${app.email.retry.interval:1} * 1000*60}")
    public void dispatchPendingWelcomeEmails(){
        List<EmailObject> emailObjects=emailObjectService.getPendingEmailsSinceNHours(retrySinceHrs);
        for (EmailObject object : emailObjects) {
            boolean status = emailSenderService.sendSimpleMessage(object);
            object.setSent(status);
            if (status) {
                log.info("Email sent status: {}",status);
                object.setSentTime(LocalDateTime.now());
            }
            object.setLastTriedAt(LocalDateTime.now());
            emailObjectService.save(object);
        }
    }

}
