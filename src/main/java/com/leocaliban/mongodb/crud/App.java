package com.leocaliban.mongodb.crud;

import org.bson.BsonDocument;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class App {
    public static void main( String[] args ){
    	
    	MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
        MongoClient client = new MongoClient(new ServerAddress(), options);
        
        MongoDatabase db = client.getDatabase("test").withReadPreference(ReadPreference.secondary());
        
        MongoCollection<BsonDocument> collection = db.getCollection("test", BsonDocument.class);
    }
}
