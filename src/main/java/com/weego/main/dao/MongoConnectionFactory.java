package com.weego.main.dao;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

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
        // MongoClientOptions.Builder builder = new
        // MongoClientOptions.Builder();
        // builder.connectionsPerHost(100);
        // builder.connectTimeout(1000 * 10);
        // builder.maxWaitTime(1000 * 10 * 2);
        // builder.threadsAllowedToBlockForConnectionMultiplier(50);
        // MongoClientOptions mongoClientOptions = builder.build();

        String host = "192.168.6.254";
        Integer port = 37017;
        mongoClient = new MongoClient(host, port);
    }

    @SuppressWarnings("unused")
    public static synchronized void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
