package com.maple.DBConnector.impl;

import com.maple.DBConnector.IDBConnector;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prodb")
public class ProConnector implements IDBConnector {
    @Override
    public void configure(){

    }
}
