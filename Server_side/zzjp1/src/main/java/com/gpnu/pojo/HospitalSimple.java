package com.gpnu.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/*
  简化的医院结果返回类，包含医院id，图片url，名字name
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalSimple {
    private Integer id;
    private String icon;
    private String hospitalname;

}
