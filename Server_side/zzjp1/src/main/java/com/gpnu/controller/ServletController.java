package com.gpnu.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gpnu.entity.*;
import com.gpnu.mapper.HospMapper;
import com.gpnu.mapper.OrderMapper;
import com.gpnu.pojo.*;
import com.gpnu.service.IFmlService;
import com.gpnu.service.IUserService;
import com.gpnu.service.IVolunService;
import com.gpnu.service.impl.HospServiceImpl;
import com.gpnu.service.impl.OrderServiceImpl;
import com.gpnu.util.Url;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RestController
public class ServletController {

    @Resource
    private IUserService iUserService;

    @Resource
    private IVolunService iVolunService;
    @Resource
    private IFmlService iFmlService;

    @Autowired
    private HospMapper hospMapper;
    @Resource
    private HospServiceImpl hospService;
    @Resource
    private OrderServiceImpl orderService;

    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping("/tabbar")
    public Images[] tabbar() {

        Images[] images = new Images[]{new Images(1, "http://"+ Url.DomainUrl +":8080/images/lunbo/lunbo1.jpg"),
                new Images(2, "http://"+ Url.DomainUrl +":8080/images/lunbo/lunbo2.jpg"),
                new Images(3, "http://"+ Url.DomainUrl+":8080/images/lunbo/lunbo3.jpg")
        };
        return images;
    }


    @RequestMapping("/gridlist")
    public Images[] gridlist() {

        Images[] images = new Images[]{new Images(1, "http://"+ Url.DomainUrl +":8080/images/grid/flower.png", "陪诊接单"),
                new Images(2, "http://"+ Url.DomainUrl +":8080/images/grid/foot.png", "陪诊记录"),
                new Images(3, "http://"+ Url.DomainUrl +":8080/images/grid/people.png", "找组织"),
                new Images(4, "http://"+ Url.DomainUrl +":8080/images/grid/shop.png", "文创商城"),
                new Images(5, "http://"+ Url.DomainUrl +":8080/images/grid/pen.png", "我有需求"),
                new Images(6, "http://"+ Url.DomainUrl +":8080/images/grid/preson.png", "我的")
        };
        return images;
    }

    @RequestMapping("/login")
    public Result login(String code, boolean checkSessionOverTime, HttpServletRequest request) {
        String responseBodyAsString = null;
        String openid = null;
        // System.out.println(code);
        HttpSession session = request.getSession();
        //如果有check请求就进行查看session是否过期
        if (checkSessionOverTime) {
            if (session.isNew()) {
                System.out.println("session已过期");
                return Result.fail("sesionid已过期");
            } else {
                System.out.println("在本地已有记录");
                return Result.ok();
            }
        } //如果没有checksessiontimeout就是第一次登录（微信小程序的cookie过期或者是第一次登录）
        //设置session超时时间
        session.setMaxInactiveInterval(60 * 60);
        //定义传输到微信服务器的地址
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxe2e1cb265379aeed&secret=ecc34a99363a27be6d543636ce9830ad&js_code=" + code + "&grant_type=authorization_code";
        //发送get请求到微信服务器的api
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);

        try {
            //发送请求
            httpClient.executeMethod(getMethod);
            //获得响应数据
            responseBodyAsString = getMethod.getResponseBodyAsString();
            //获得json数据里面包含openid（用来标识客户建表用）和sessionid
            //System.out.println(responseBodyAsString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将json字符串转化为hashmap
        HashMap<String, String> responseMap = JSON.parseObject(responseBodyAsString, HashMap.class);
        //获取openid
        openid = responseMap.get("openid");
        //将session_key保存到session
        session.setAttribute("session_key", responseMap.get("session_key"));
        // 根据openid查找用户
        User user = iUserService.query().eq("openid", openid).one();
        System.out.println(user);
        // 用户不存在
        if (user == null) {
            createUserWithOpenid(openid);
        }
        System.out.println(user);
        return Result.ok(user, openid);
    }

    @RequestMapping("/saveoldermessage")
    public void saveOlderMessage(@RequestBody String dataOfJson) {
        HashMap<String, String> hashMap = JSON.parseObject(dataOfJson, HashMap.class);
        //这是获取的数据，这些长者数据直接放在用户表不需要建多一个表
        System.out.println(hashMap.get("gender") + hashMap.get("name") + hashMap.get("phoneNumber") +
                hashMap.get("identity") + "openid:" + hashMap.get("openid"));

        //将数据插入到tb_user
        UpdateAllForUser(hashMap);
    }


    @RequestMapping("/savehomemessage")
    public void saveHomeMessage(@RequestBody String dataOfJson, HttpServletRequest res) {
        HashMap<String, String> hashMap = JSON.parseObject(dataOfJson, HashMap.class);
        HttpSession session = res.getSession();
        //这是获取的数据，identity，name，homePhoneNumber放在用户表，oldPhoneNumber放在家属表，
        //也就是家属表要openid，oldphoneNumber字段（要新建,起名为tb_family）
        String openid = hashMap.get("openid");
        System.out.println(hashMap.get("gender") + hashMap.get("name") + hashMap.get("PhoneNumber") +
                hashMap.get("oldPhoneNumber") + hashMap.get("identity") + hashMap.get("openid"));
        //将数据插入到数据库
        updateForUser(hashMap);

        // 将数据插入到tb_family
        Family family = iFmlService.getById(hashMap.get("openid"));
        if (family == null) {
            Family f = new Family();
            f.setOpenid(hashMap.get("openid"));
            f.setPhone(hashMap.get("oldPhoneNumber"));
            iFmlService.save(f);
        } else {
            family.setPhone(hashMap.get("oldPhoneNumber"));
            iFmlService.updateById(family);
        }
    }


    @RequestMapping("/savevolunmessage")
    public void saveVolunMessage(@RequestBody String dataOfJson) {
        HashMap<String, String> hashMap = JSON.parseObject(dataOfJson, HashMap.class);
        //这是获取的数据，identity，name，phoneNumber放在用户表，school放在自愿者表，
        //也就是自愿者表表要openid，school字段（要新建,起名为tb_volunteer）

        // 将数据插入到tb_user,
        updateForUser(hashMap);

        // 将数据插入到tb_volunteer
        Volunteer volunteer = iVolunService.getById(hashMap.get("openid"));
        if (volunteer == null) {
            Volunteer v = new Volunteer();
            v.setOpenid(hashMap.get("openid"));
            v.setSchool(hashMap.get("school"));
            iVolunService.save(v);
        } else {
            volunteer.setSchool(hashMap.get("school"));
            iVolunService.updateById(volunteer);
        }
    }


    //**** 5.1接口****（实现）
    //**通过客户定位获得医院数组接口**
    @RequestMapping("/showhospitallist")
    public List<HospitalSimple> showhostpitallist(Double x, Double y) {
        //x是客户的经度，y是客户的维度，查询十公里以内的医院（x-x1）^2 + (y-y1)^2 <= 100（x1，y1是查询目的医院的经纬度）
        System.out.println(x + "  " + y);
        if(x!=null && y!=null){
            List<HospitalSimple> hospitalList = hospMapper.selectHospitailByLocation(x, y);
            System.out.println(hospitalList);
            return hospitalList;
        }
        return null;

    }


    //**** 5.1接口****（实现）
    //**显示医院详细信息接口**需要建医院表（ id，名字，医院描述，地址，坐标，医院图片的url）
    @RequestMapping("/showdetailhospital")
    public Hospital showdetailhospital(int id) {
        //id是前端传过来的要查询详细信息医院的id值，返回医院的详细信息到前端
        Hospital hospital = hospService.getBaseMapper().selectById(id);
        hospital.setIcon("http://localhost:8080/images/hostpitalphotoes/img.png");
        System.out.println(hospital);
        //返回值的样式
        return hospital;

    }

    //**** 5.1接口****
    //通过客户输入客户信息，搜索相近结果的医院数组
    @RequestMapping("/searchhospitallist")
    public List<HospitalSimple> searnchHospitallist(String message) {//message是用户输入的搜索信息
        System.out.println(message);

        //返回搜索的数组
        return null;
    }


    //**** 5.1接口****(实现)
    //***创建订单接口****
    @RequestMapping("/creatOrder")
    public boolean creatOrder(@RequestBody Order order) {
        System.out.println(order);
        //将order存入数据库中
       return orderService.save(order);

    }


    //****5.2*****(实现)
    //****志愿者查询接单接口****
    @RequestMapping("/searchOrder")
    public List<VloOrderSimple> VloOrder(Double x, Double y) {


        if (x!=null && y!=null ){
            List<VloOrderSimple> orders = orderMapper.searchOrder(x,y);
            return orders;
        }
        return null;
    }

    //****5.14***(实现)
    //*****志愿者接受订单接口*****
    @RequestMapping("/accpectOrder")
    public  boolean accpectOrder(@RequestBody AccpectOrderPOJO accpectOrderPOJO){

        UpdateWrapper<Order> orderUpdateWrapper = new UpdateWrapper<Order>();
        orderUpdateWrapper.eq("id", accpectOrderPOJO.getOrderId())
                          .set("vlo_id", accpectOrderPOJO.getOpenId());
        //志愿者接单效果就是把订单表的志愿者id补全
        boolean update = orderService.update(orderUpdateWrapper);

        return update;
    }


    //****5.15****
    //**志愿者（老人）查找自己所有接（发布）的订单（未完成）***
    @RequestMapping("/searchHistoryOrderNofinish")
    public List<NoFinish> searchHistoryOrder( String openid){
        List<NoFinish> result;
        //先判断这个人的身份
        Integer state = orderMapper.state(openid);
        if (state==0)//如果是志愿者
            result= orderService.getBaseMapper().searchNoFinishListByVlo(openid);
        else //如果是老人
            result= orderService.getBaseMapper().searchNoFinishListByOld(openid);
        return result;
    }


    //*****5.20*****
    //**志愿者（老人）查找自己所有接（发布）的订单（已完成）***
    @RequestMapping("/searchHistoryOrderHavefinish")
    public List<HaveFinished> searchHistoryOrderHaveFinish(String openid){
        List<HaveFinished> result;
        Integer state = orderMapper.state(openid);
        if (state==0)//如果是志愿者
            result= orderService.getBaseMapper().searchHaveFinishedByVlo(openid);
        else //如果是老人
            result= orderService.getBaseMapper().searchHaveFinishedByOld(openid);
        return result;
    }
    
    
    //***5.21***
    //查找订单的state，也就是到哪一个流程
    @RequestMapping("/searchOrderState")
    public Integer searchOrderState(Long orderId){

        Integer i = orderMapper.searchOrderState(orderId);
        if (i==null)
            i=-1;
        return i;
    }

    @RequestMapping("/startOrder")
    public Boolean startOrder(Long orderId){
        //获得当前时间
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = formatter.format(date);
        //插入数据
        Order order = new Order();
        order.setId(orderId);
        order.setState(0);
        order.setStartTime(dateTime);
        if (orderMapper.updateById(order)==0)
            return false;
        else
            return true;
    }



    @RequestMapping("/stepPuls")
    public Boolean stepPuls(Long orderId){
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.eq("id",orderId);
        wrapper.setSql("`state` = `state` + 1");
        return orderService.update(wrapper);
    }

    @RequestMapping("/finishOrder")
    public Boolean finishOrder(Long orderId){
        //获得当前时间
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = formatter.format(date);
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.eq("id",orderId);
        wrapper.set("state", 10);
        wrapper.set("finish_data",dateTime);
        return orderService.update(wrapper);
    }
    

    private void updateForUser(HashMap<String, String> hashMap) {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("openid", hashMap.get("openid"));
        userUpdateWrapper.set("identity", Integer.parseInt(hashMap.get("identity")));
        userUpdateWrapper.set("real_name", hashMap.get("name"));
        userUpdateWrapper.set("phone", hashMap.get("phoneNumber"));
        iUserService.update(null, userUpdateWrapper);
    }


    private void createUserWithOpenid(String openid) {
        User user = new User();
        user.setOpenid(openid);
        iUserService.save(user);
    }


    private void UpdateAllForUser(HashMap<String, String> hashMap) {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("openid", hashMap.get("openid"));
        userUpdateWrapper.set("gender", hashMap.get("gender"));
        userUpdateWrapper.set("real_name", hashMap.get("name"));
        userUpdateWrapper.set("phone", hashMap.get("phoneNumber"));
        userUpdateWrapper.set("identity", Integer.parseInt(hashMap.get("identity")));
        iUserService.update(null, userUpdateWrapper);
    }

}
