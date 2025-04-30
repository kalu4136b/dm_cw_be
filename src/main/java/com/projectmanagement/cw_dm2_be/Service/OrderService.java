package com.projectmanagement.cw_dm2_be.Service;
import com.projectmanagement.cw_dm2_be.Repository.OrderRepository;
import com.projectmanagement.cw_dm2_be.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    public void deleteOrder(int id) {
        orderRepository.deleteOrder(id);
    }

    public Order getOrderById(int id) {
        return orderRepository.getOrderById(id);
    }
}
