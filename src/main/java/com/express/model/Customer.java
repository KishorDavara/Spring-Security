package com.express.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO , generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private Long id;

    private String email;

    private String pwd;

    private String role;
}
