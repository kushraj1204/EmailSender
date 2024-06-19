package edu.miu.emailsender.service;

import edu.miu.emailsender.domain.EmailObject;
import edu.miu.emailsender.dto.EmailObjectDto;

/**
 * @author kush
 */
public interface EmailSenderService {
    boolean sendSimpleMessage(EmailObject emailObject);
    boolean sendSimpleMessage(EmailObjectDto emailObjectDto);
}
