package com.express.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
public class AccountTransactions {
    @Id
    private String transactionId;

    private long accountNumber;

    private int customerId;

    private Date transactionDt;

    private String transactionSummary;

    private String transactionType;

    private int transactionAmt;

    private int closingBalance;

    private String createDt;
}
