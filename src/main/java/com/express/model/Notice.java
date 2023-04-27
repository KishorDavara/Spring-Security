package com.express.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "notice_details")
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
