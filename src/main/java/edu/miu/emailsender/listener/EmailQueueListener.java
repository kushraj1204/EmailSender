package edu.miu.emailsender.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.miu.emailsender.domain.EmailObject;
import edu.miu.emailsender.dto.EmailObjectDto;
import edu.miu.emailsender.mapper.DtoMapper;
import edu.miu.emailsender.service.EmailObjectService;
import edu.miu.emailsender.service.EmailSenderService;
import edu.miu.emailsender.util.StaticUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author kush
 */
@Component
@Slf4j
public class EmailQueueListener {

    final
    EmailObjectService emailObjectService;

    final EmailSenderService emailSenderService;

    public EmailQueueListener(EmailObjectService emailObjectService, EmailSenderService emailSenderService) {
        this.emailObjectService = emailObjectService;
        this.emailSenderService = emailSenderService;
    }

//    @JmsListener(destination = "emailQueue")
    public void receiveMessage(final String strInput) {
        EmailObjectDto objectDto = null;
        try {
            objectDto = StaticUtils.mapper.readValue(strInput, EmailObjectDto.class);
            EmailObject object = DtoMapper.dtoMapper.emailObjectDtoToEmailObject(objectDto);
//            boolean status = emailSenderService.sendSimpleMessage(objectDto);
            boolean status=false;
            object.setSent(status);
            if (status) {
                log.info("Email sent status: {}", status);
                object.setSentTime(LocalDateTime.now());
            }
            object.setEntryTime(LocalDateTime.now());
            object.setLastTriedAt(LocalDateTime.now());
            emailObjectService.save(object);
        } catch (JsonProcessingException e) {
            log.error("Error parsing the received object into EmailObject {}", strInput);
        }

    }
}