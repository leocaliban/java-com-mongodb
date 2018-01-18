package com.leocaliban.mongodb.crud;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.leocaliban.util.Helpers.printJson;

public class InsertTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("pessoadb");
		MongoCollection<Document> collection = db.getCollection("testePessoas");
		
		collection.drop();
		
		Document pessoa = new Document("nome", "Jack Bauer")
							.append("idade", 37)
							.append("profissao", "Ator");
		
		collection.insertOne(pessoa);

		printJson(pessoa);
	}

}
