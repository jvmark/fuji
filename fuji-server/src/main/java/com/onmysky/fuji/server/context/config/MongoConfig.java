package com.onmysky.fuji.server.context.config;

import com.google.common.collect.Lists;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.omg.CORBA.portable.UnknownException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by mark on 15/10/16.
 */
@Configuration
public class MongoConfig {

    @Value("#{'${mongo.rs1.url}'.split(',')}")
    private List<String> mongors1Urls;

    @Value("#{'${mongo.rs2.url}'.split(',')}")
    private List<String> mongors2Url;

    private static int port = 27017;

    @Bean
    public Mongo rs1Mongo() throws UnknownException {
        List<ServerAddress> serverAddress = Lists.newArrayList();
        for (String mongors1Url : mongors1Urls) {
            serverAddress.add(new ServerAddress(mongors1Url, port));
        }
        return new MongoClient(serverAddress);
    }

    @Bean
    public MongoTemplate wordDB() throws UnknownHostException {
        return new MongoTemplate(rs1Mongo(), "wordDB");
    }

    @Bean
    public MongoTemplate wechatDB() throws UnknownHostException {
        return new MongoTemplate(rs1Mongo(), "wechat_token");
    }
}
