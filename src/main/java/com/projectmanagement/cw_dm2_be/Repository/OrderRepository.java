package com.projectmanagement.cw_dm2_be.Repository;


import com.projectmanagement.cw_dm2_be.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;

import java.util.Map;
import java.sql.Types;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addOrder(Order order) {
        jdbcTemplate.update("CALL add_order(?, ?, ?, ?, ?)",
                order.getUserId(),
                order.getProductName(),
                order.getQuantity(),
                order.getTotalPrice(),
                new java.sql.Date(order.getOrderDate().getTime())
        );
    }

    public void deleteOrder(int id) {
        jdbcTemplate.update("CALL delete_order(?)", id);
    }

    public Order getOrderById(int id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("get_order_by_id")
                .declareParameters(
                        new SqlParameter("p_id", Types.INTEGER),
                        new SqlOutParameter("p_user_id", Types.INTEGER),
                        new SqlOutParameter("p_product_name", Types.VARCHAR),
                        new SqlOutParameter("p_quantity", Types.INTEGER),
                        new SqlOutParameter("p_total_price", Types.DOUBLE),
                        new SqlOutParameter("p_order_date", Types.DATE)
                );

        Map<String, Object> result = jdbcCall.execute(id);

        Order order = new Order();
        order.setId(id);
        order.setUserId((Integer) result.get("p_user_id"));
        order.setProductName((String) result.get("p_product_name"));
        order.setQuantity((Integer) result.get("p_quantity"));
        order.setTotalPrice((Double) result.get("p_total_price"));
        order.setOrderDate((java.sql.Date) result.get("p_order_date"));

        return order;
    }

}
