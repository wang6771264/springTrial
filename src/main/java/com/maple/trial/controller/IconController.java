package com.maple.trial.controller;

import com.maple.trial.config.DBConfig;
import com.maple.trial.config.MapleConfig;
import com.maple.trial.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maple")
public class IconController {
    @Autowired
    DBConfig dbConfig;

    @Autowired
    MapleConfig mapleConfig;

    @RequestMapping("/")
    public Object index(String input){
        User user = new User();
        user.setUserName(dbConfig.getUsername());
        user.setPassword(dbConfig.getPassword());
        user.setFirst(mapleConfig.getFirst());
        user.setSecond(mapleConfig.getSecond());
        return user;
    }
}
