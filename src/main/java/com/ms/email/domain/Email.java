package com.ms.email.domain;

import com.ms.email.enums.StatusEmail;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "TBL_EMAIL")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID emailId;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subjetc;

    @Column(columnDefinition = "TEXT")
    private String text;

    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;
}
