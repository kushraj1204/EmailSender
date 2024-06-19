package edu.miu.emailsender.repository;

import edu.miu.emailsender.domain.EmailObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author kush
 */
public interface EmailObjectRepository extends JpaRepository<EmailObject,Long> {


    @Query("SELECT e FROM EmailObject e WHERE e.isSent = false AND e.entryTime > :targetDateTime")
    List<EmailObject> getPendingEmailsSince(LocalDateTime targetDate);
}
