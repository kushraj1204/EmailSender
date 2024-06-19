package edu.miu.emailsender.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author kush
 */
@Entity
@Data
@AllArgsConstructor
public class EmailObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,name = "from_email")
    private String from;

    @Column(nullable = false,name = "to_email")
    private String to;

    private String cc;
    private String bcc;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false,name="content_type")
    private String contentType;

    //attachments not included for now
    private boolean isSent;

    @Column(nullable = false,name = "entry_time")
    private LocalDateTime entryTime;

    @Column(nullable = false,name = "sent_time")
    private LocalDateTime sentTime;

    @Column(nullable = false,name = "last_tried_at")
    private LocalDateTime lastTriedAt;

    public EmailObject() {

        this.contentType = "text/plain";
    }

    List<String> getToList(){
        return List.of(this.to.split(","));
    }
    List<String> getCcList(){
        return List.of(this.cc.split(","));
    }
    List<String> getBccList(){
        return List.of(this.bcc.split(","));
    }

}
