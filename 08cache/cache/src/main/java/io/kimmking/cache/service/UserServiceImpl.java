package io.kimmking.cache.service;

import io.kimmking.cache.entity.User;
import io.kimmking.cache.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper; //DAO  // Repository

    /*
    区别
    127.0.0.1:6379> keys *
        1) "users::io.kimmking.cache.service.UserServiceImpl.list."
        9) "userCache::1"
     */
    // 开启spring cache
    @Cacheable(key="#id",value="userCache",unless="#result == null")
    public User find(int id) {
        System.out.println(" ==> find " + id);
        return userMapper.find(id);
    }

    // 开启spring cache
    @Cacheable //(key="methodName",value="userCache")
    public List<User> list(){
        return userMapper.list();
    }

}
