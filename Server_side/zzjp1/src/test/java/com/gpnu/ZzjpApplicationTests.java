package com.gpnu;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gpnu.entity.Hospital;
import com.gpnu.entity.Order;
import com.gpnu.entity.Volunteer;
import com.gpnu.mapper.HospMapper;
import com.gpnu.mapper.OrderMapper;
import com.gpnu.pojo.HaveFinished;
import com.gpnu.pojo.NoFinish;
import com.gpnu.pojo.VloOrderSimple;
import com.gpnu.service.IHospService;
import com.gpnu.service.IOrderService;
import com.gpnu.service.IVolunService;
import com.gpnu.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ZzjpApplicationTests {

	@Resource
	private IVolunService iVolunService;

	@Resource
	private IHospService iHospService;

	@Resource
	private IOrderService iOrderService;

	@Resource
	private OrderMapper orderMapper;

	@Resource
	private HospMapper hospMapper;



	@Test
	void contextLoads() {
		UpdateWrapper<Volunteer> volunteerUpdateWrapper = new UpdateWrapper<>();
		volunteerUpdateWrapper.eq("openid","15");
		volunteerUpdateWrapper.set("school","牛马广师大");
		System.out.println(iVolunService.update(null, volunteerUpdateWrapper));
		System.out.println(iVolunService.getById(15));
	}

	@Test
	void PointTest() {
		boolean result = iHospService.save(new Hospital(3, "123", 1.3,1.5, "123", "151", "151"));
		System.out.println(result);
	}

	@Resource
	OrderServiceImpl orderService;
	@Test
	void OrderTest() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String dateTime = formatter.format(date);
		System.out.println(dateTime);

	}
}
