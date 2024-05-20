package com.gpnu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.locationtech.jts.io.WKTWriter;
import org.springframework.data.geo.Point;

import javax.persistence.Column;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_hospital")
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {

    @TableId(value = "id")
    private Integer id;

    private String icon;

    // 经度
    private double longitude;

    // 维度
    private double latitude;

    private String description;
    private String hospitalName;
    private String location;

}
