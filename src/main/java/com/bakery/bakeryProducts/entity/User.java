package com.bakery.bakeryProducts.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId;
    String  name;
    String username;
    String password;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private UserRole userRole;
    boolean status;
    String createdBy;
    @Temporal(TemporalType.DATE)
    Date createdDate;
}
