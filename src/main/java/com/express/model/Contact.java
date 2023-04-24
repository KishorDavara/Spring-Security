package com.express.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
public class Contact {
    @Id
    private String contactId;

    private String contactName;

    private String contactEmail;

    private String subject;

    private String message;

    private Date createDt;
}
