package com.projectmanagement.cw_dm2_be.Service;

import com.projectmanagement.cw_dm2_be.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public void addItemToCart(Long cartId, Long productId, int quantity, double price) {
        cartRepository.addToCart(cartId, productId, quantity, price);
    }
}
