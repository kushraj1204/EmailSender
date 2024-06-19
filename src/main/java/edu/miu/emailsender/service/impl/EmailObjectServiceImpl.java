package edu.miu.emailsender.service.impl;

import edu.miu.emailsender.domain.EmailObject;
import edu.miu.emailsender.repository.EmailObjectRepository;
import edu.miu.emailsender.service.EmailObjectService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author kush
 */
@Service
public class EmailObjectServiceImpl implements EmailObjectService {

    final
    EmailObjectRepository emailObjectRepository;

    public EmailObjectServiceImpl(EmailObjectRepository emailObjectRepository) {
        this.emailObjectRepository = emailObjectRepository;
    }

    @Override
    public EmailObject save(EmailObject object) {
        return emailObjectRepository.save(object);
    }

    @Override
    public List<EmailObject> getPendingEmailsSinceNHours(int n) {
        LocalDateTime targetDate = LocalDateTime.now().minusHours(n);
        return emailObjectRepository.getPendingEmailsSince(targetDate);
    }
}
