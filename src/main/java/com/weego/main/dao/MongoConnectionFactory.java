package com.weego.main.dao;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class MongoConnectionFactory {
    private static MongoClient mongoClient = null;

    public static DB getDatabase() {
        DB database;

        if (mongoClient == null) {
            try {
                initializeMongoClient();
            } catch (UnknownHostException e) {
                e.printStackTrace();
                System.out.println("数据库服务器不可用");
            }
        }

        String databaseName = "travel2";
        database = mongoClient.getDB(databaseName);
        return database;
    }

    private static void initializeMongoClient() throws UnknownHostException {
//        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
//        builder.connectionsPerHost(100);
//        builder.connectTimeout(1000 * 10);
//        builder.maxWaitTime(1000 * 10 * 2);
//        builder.threadsAllowedToBlockForConnectionMultiplier(50);
//        MongoClientOptions mongoClientOptions = builder.build();

        String host = "192.168.6.254";
        Integer port = 37017;
        mongoClient = new MongoClient(host, port);
    }


    public static MongoDatabase getMongodb() {
        MongoDatabase mongoDB = null;
        if (mongoDB == null) {

            try {

                ServerAddress sa = new ServerAddress("127.0.0.1", 27017);
                List<ServerAddress> sends = new ArrayList<ServerAddress>();
                sends.add(sa);
                List<MongoCredential> mongoCredentialList = new ArrayList<MongoCredential>();
                mongoCredentialList.add(MongoCredential.createCredential("weego", "admin","weego".toCharArray()));
                mongoDB = new MongoClient(sends,mongoCredentialList).getDatabase("travel");

            } catch (Exception e) {

                throw new RuntimeException("连接MongoDB数据库错误", e);

            }

        }

        return mongoDB;
    }

    @SuppressWarnings("unused")
    public static synchronized void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
