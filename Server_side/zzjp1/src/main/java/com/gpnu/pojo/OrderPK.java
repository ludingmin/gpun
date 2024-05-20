package com.gpnu.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class OrderPK implements Serializable {
    // 接单的志愿者id字段
    @Column(name = "volId" +
            "")
    private String volId;

    @Column(name = "openid")
    private String openid;
}
