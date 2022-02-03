package com.bakery.bakeryProducts.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    int roleId;
    String roleName;
    String createdBy;
    @Temporal(TemporalType.DATE)
    Date createdDate;
}
