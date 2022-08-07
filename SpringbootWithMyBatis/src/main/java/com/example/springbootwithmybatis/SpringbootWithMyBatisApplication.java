package com.example.springbootwithmybatis;

import com.example.springbootwithmybatis.domain.Order;
import com.example.springbootwithmybatis.domain.User;
import com.example.springbootwithmybatis.mapper.UserMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringbootWithMyBatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWithMyBatisApplication.class, args);
        AbstractXmlApplicationContext ctxt = new ClassPathXmlApplicationContext(
                "configuration.xml"
        );
        /*
        UserMapper userMapper = (UserMapper) ctxt.getBean("userMapper");
        // 測試id=1的用戶查詢，可根據數據庫中的情況修改.
        User user = userMapper.getUserById(1);
        System.out.println("獲取用戶 ID=1 的用戶名：" + user.getUserName());

        // 得到文章列表測試
        System.out.println("得到用戶id為1的所有訂單列表:");
        System.out.println("=============================================");
        List<Order> orders = userMapper.getUserOrders(1);

        for (Order order : orders) {
            System.out.println("訂單號：" + order.getOrderNo() + "，訂單金額：" + order.getMoney());
        }
         */
        ctxt.close();
    }
}
