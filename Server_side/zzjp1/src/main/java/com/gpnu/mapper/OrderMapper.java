package com.gpnu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gpnu.entity.Order;
import com.gpnu.pojo.HaveFinished;
import com.gpnu.pojo.NoFinish;
import com.gpnu.pojo.VloOrderSimple;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface OrderMapper extends BaseMapper<Order> {

    //按照直径距离查询出的五公里内的订单
    @Select("SELECT tb_order.id as order_id," +
            "tb_hospital.hospital_name," +
            "tb_user.real_name as old_name," +
            "tb_order.vlo_id as vloid," +
            "tb_order.location_of_latitude as latitude," +
            "tb_order.location_of_longitude as longitude," +
            "ROUND(6378.138*2*ASIN(SQRT(POW(SIN((#{x}*PI()/180-location_of_latitude*PI()/180)/2),2)" +
            "+ COS(#{x}*PI()/180)*COS(location_of_latitude*PI()/180)*" +
            "POW(SIN((#{y}*PI()/180 - location_of_longitude*PI()/180)/2),2)))*1000) as distance" +
            " FROM tb_order join tb_user on " +
            " tb_order.old_id=tb_user.openid join tb_hospital on tb_order.hospital_id=tb_hospital.id " +
            "having `distance` <=5000  and vloid is null order by `distance` asc")
    public List<VloOrderSimple> searchOrder(Double x, Double y);


    @Select("select identity from tb_user where openid = #{openid}")
    public Integer state(String openid);

    @Select("select tb_order.id," +"tb_user.phone as phone,"+
            "tb_hospital.hospital_name as hospitalName," +
            "tb_user.real_name as name," +
            "tb_order.creat_data as data," +
            "tb_order.location_of_latitude as latitude," +
            "tb_order.location_of_longitude as longitude " +
            "from tb_order join tb_user on tb_order.old_id = tb_user.openid " +
            "join tb_hospital on tb_order.hospital_id = tb_hospital.id where tb_order.vlo_id=#{openid} and ( tb_order.state != 10 or tb_order.state is null)")
    //订单表中不符合state=10，的志愿者
    public List<NoFinish> searchNoFinishListByVlo(String openid);



    @Select("select "+
            "tb_hospital.hospital_name as hospitalName," +
            "tb_order.creat_data as data," +
            "tb_order.location_of_latitude as latitude," +
            "tb_order.location_of_longitude as longitude " +
            "from tb_order " +
            "join tb_hospital on tb_order.hospital_id = tb_hospital.id where tb_order.old_id=#{openid} and tb_order.vlo_id is  null")
    //订单表中符合志愿者栏为空的（代表无人接单）
    public List<NoFinish> searchNoFinishListByOld(String openid);




    @Select("SELECT  tb_user.real_name as name,tb_hospital.hospital_name, creat_data " +
            "as creatTime,timediff(finish_data,start_time) as serviceTime ,tb_user.phone as phone FROM tb_order " +
            "join  tb_user on tb_order.old_id = tb_user.openid " +
            "join tb_hospital on tb_hospital.id = tb_order.hospital_id where tb_order.vlo_id=#{openid} and tb_order.state=10;")
    public List<HaveFinished> searchHaveFinishedByVlo(String openid);


    @Select("SELECT  tb_user.real_name as name,tb_hospital.hospital_name, creat_data " +
            "as creatTime,timediff(finish_data,start_time) as serviceTime ,tb_user.phone as phone FROM tb_order " +
            "join  tb_user on tb_order.vlo_id = tb_user.openid " +
            "join tb_hospital on tb_hospital.id = tb_order.hospital_id where tb_order.old_id=#{openid} and tb_order.vlo_id is not null;"
    )
    public List<HaveFinished> searchHaveFinishedByOld(String openid);


    @Select("select state from tb_order where id=#{orderId}")
    public Integer searchOrderState(Long orderId);






}
