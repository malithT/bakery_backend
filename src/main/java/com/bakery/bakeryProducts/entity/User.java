package com.bakery.bakeryProducts.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    String createdDate;
}
