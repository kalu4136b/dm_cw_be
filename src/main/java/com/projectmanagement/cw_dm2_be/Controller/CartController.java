package com.projectmanagement.cw_dm2_be.Controller;

import com.projectmanagement.cw_dm2_be.Model.CartItemDTO;
import com.projectmanagement.cw_dm2_be.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody CartItemDTO item) {
        cartService.addItemToCart(item.getCartId(), item.getProductId(), item.getQuantity(), item.getPrice());
        return ResponseEntity.ok("Item added to cart");
    }
}

