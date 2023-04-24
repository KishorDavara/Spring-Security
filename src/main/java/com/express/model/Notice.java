package com.express.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@Entity
@Getter
@Setter
public class Notice {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int noticeId;

    private String noticeSummary;

    private String noticeDetails;

    private Date noticBegDt;

    private Date noticEndDt;

    private Date createDt;

    private Date updateDt;
}
