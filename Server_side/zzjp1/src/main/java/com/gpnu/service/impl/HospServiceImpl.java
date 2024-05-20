package com.gpnu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpnu.entity.Hospital;
import com.gpnu.mapper.HospMapper;
import com.gpnu.service.IHospService;
import org.springframework.stereotype.Service;

@Service
public class HospServiceImpl extends ServiceImpl<HospMapper, Hospital> implements IHospService {

}
