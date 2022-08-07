package com.example.springbootwithmybatis.mapper;

import com.example.springbootwithmybatis.domain.Order;
import com.example.springbootwithmybatis.domain.User;

import java.util.List;

/// mapping to

public interface UserMapper {

    public User getUserById(int id);
    public List<Order> getUserOrders(int id);

}
