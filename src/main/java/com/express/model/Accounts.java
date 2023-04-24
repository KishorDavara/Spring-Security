package com.express.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Accounts {
    @Id
    private long accountNumber;

    private String accountType;

    private String branchAddress;

    private int customerId;

    private String createDt;
}
