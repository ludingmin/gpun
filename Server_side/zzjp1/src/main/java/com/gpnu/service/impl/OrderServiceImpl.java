package com.gpnu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpnu.entity.Order;
import com.gpnu.mapper.OrderMapper;
import com.gpnu.service.IOrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
}
