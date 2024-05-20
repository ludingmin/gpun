package com.gpnu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpnu.entity.Volunteer;
import com.gpnu.mapper.VolunMapper;
import com.gpnu.service.IVolunService;
import org.springframework.stereotype.Service;

@Service
public class VolunServiceImpl extends ServiceImpl<VolunMapper, Volunteer> implements IVolunService {
}
