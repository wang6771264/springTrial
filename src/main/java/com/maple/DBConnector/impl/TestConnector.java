package com.maple.DBConnector.impl;

import com.maple.DBConnector.IDBConnector;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("testdb")
public class TestConnector implements IDBConnector{
    @Override
    public void configure(){

    }
}
