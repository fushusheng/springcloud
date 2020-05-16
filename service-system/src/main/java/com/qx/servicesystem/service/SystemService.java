package com.qx.servicesystem.service;

import com.qx.common.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(isolation =Isolation.READ_COMMITTED)
public class SystemService {
    public User login(String userName,String password){
        //查询数据库
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail("123@qq.com");
        user.setBirthday(new Date());
        return user;
    }
}
