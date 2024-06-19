package edu.miu.emailsender.service;

import edu.miu.emailsender.domain.EmailObject;

import java.util.List;

/**
 * @author kush
 */
public interface EmailObjectService {
    EmailObject save(EmailObject object);

    List<EmailObject> getPendingEmailsSinceNHours(int i);
}
