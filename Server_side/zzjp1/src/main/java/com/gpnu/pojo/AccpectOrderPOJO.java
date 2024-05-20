package com.gpnu.pojo;


import lombok.Data;

@Data
//用来接受订单的pojo辅助类
public class AccpectOrderPOJO {

    private Long orderId;
    private String openId;
}
