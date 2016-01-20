package com.cyyz.novel.service;

import com.cyyz.novel.bean.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {



    public User findById(Integer id){
        return new User();
    }
}
