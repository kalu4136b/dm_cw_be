package com.projectmanagement.cw_dm2_be.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "CART_ITEM")
public class Cart {




        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "CART_ID")
        private Long cartId;

        @Column(name = "PRODUCT_ID")
        private Long productId;

        @Column(name = "QUANTITY")
        private int quantity;

        @Column(name = "PRICE")
        private double price;







}
