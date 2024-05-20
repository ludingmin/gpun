package com.gpnu.pojo;


import lombok.Data;

@Data
public class VloOrderSimple {
    private Long orderId;
    private Double latitude;
    private Double longitude;
    private String oldName;
    private String hospitalName;
}
