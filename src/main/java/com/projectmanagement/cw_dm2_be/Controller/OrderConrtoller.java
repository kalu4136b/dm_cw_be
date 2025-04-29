package com.projectmanagement.cw_dm2_be.Controller;
import com.projectmanagement.cw_dm2_be.Model.Order;
import com.projectmanagement.cw_dm2_be.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")

public class OrderConrtoller {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        orderService.addOrder(order);
        return ResponseEntity.ok("Order placed successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable int id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

}
