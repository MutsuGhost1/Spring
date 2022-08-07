package com.example.springbootwithmybatis.domain;

/*
    CREATE TABLE `order` (
      `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
      `user_id` int(10) unsigned NOT NULL DEFAULT '0',
      `order_no` varchar(16) NOT NULL DEFAULT '',
      `money` float(10,2) unsigned DEFAULT '0.00',
      PRIMARY KEY (`order_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
 */

public class Order {
    private int orderId;
    private int userId;
    private String orderNo;
    private float money;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
