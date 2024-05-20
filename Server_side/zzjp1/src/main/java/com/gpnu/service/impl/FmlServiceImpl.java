package com.gpnu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpnu.entity.Family;
import com.gpnu.mapper.FmlMapper;
import com.gpnu.service.IFmlService;
import org.springframework.stereotype.Service;

@Service
public class FmlServiceImpl extends ServiceImpl<FmlMapper, Family> implements IFmlService {
}
