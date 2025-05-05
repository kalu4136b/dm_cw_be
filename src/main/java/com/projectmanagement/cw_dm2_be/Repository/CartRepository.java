package com.projectmanagement.cw_dm2_be.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addToCart(Long cartId, Long productId, int quantity, double price) {
        jdbcTemplate.update("CALL ADD_TO_CART(?, ?, ?, ?)", cartId, productId, quantity, price);
    }
}
