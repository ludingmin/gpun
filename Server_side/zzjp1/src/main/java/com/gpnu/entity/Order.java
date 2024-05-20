package com.gpnu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gpnu.pojo.OrderPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.EmbeddedId;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_order")
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    // connectionPhoneNumber联系电话，字符串类型
    // isInfectiousDiseases，是否有传染病，值是字符串形式的’true‘和’false’
    // location，地址
    // HistoricalCases病史 字符串类型
    //id是医院id
    //openid是用户id
    //*******************************
    //订单表需求，要包括上面6个字段，还需要增加是否已经被接单的字段，和接单的志愿者id字段。 共八个
    @TableId(type = IdType.AUTO)
    private Long id;
    private String connectionNumber;
    private Boolean isInfectiousDiseases;
    private Double locationOfLongitude;
    private Double locationOfLatitude;
    private String historicalCases;
    private String oldId;
    private String vloId;
    private Integer hospitalId;
    private Integer state;

    private String creatData;
    private String finishData;
    private String startTime;


}
