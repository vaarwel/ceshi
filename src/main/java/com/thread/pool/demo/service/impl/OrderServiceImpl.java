package com.thread.pool.demo.service.impl;

import com.thread.pool.demo.order.AbstractOrderStartegy;
import com.thread.pool.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private AbstractOrderStartegy abstractOrderStartegy;

    @Override
    public String addOrder(String orderNo) {
        System.out.println("hahahah");
        return abstractOrderStartegy.addOrder(orderNo);
    }
}
