package com.gpnu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gpnu.entity.Hospital;
import com.gpnu.pojo.HospitalSimple;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface HospMapper extends BaseMapper<Hospital> {

    @Select("SELECT *,ROUND(6378.138*2*ASIN(SQRT(POW(SIN((#{y}*PI()/180-latitude*PI()/180)/2),2) " +
            "+ COS(#{y}*PI()/180)*COS(latitude*PI()/180)*" +
            "POW(SIN((#{x}*PI()/180 - longitude*PI()/180)/2),2)))*1000) " +
            "AS distance FROM tb_hospital " +
            "having distance <= 5000 order by distance asc")
    public List<HospitalSimple> selectHospitailByLocation(Double x, Double y);
}
