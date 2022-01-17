package com.bakery.bakeryProducts.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "product_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_id")
    int productCategoryId;
    String categoryName;
}