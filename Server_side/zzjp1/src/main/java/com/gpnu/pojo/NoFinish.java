package com.gpnu.pojo;


import lombok.Data;

@Data
public class NoFinish {

    private Long id;
    private String name;
    private String hospitalName;
    private String data;
    private Double latitude;
    private Double longitude;
    private String phone;

    private String address = "没有地址" ;

}
