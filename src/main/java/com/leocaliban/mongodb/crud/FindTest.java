package com.leocaliban.mongodb.crud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.leocaliban.util.Helpers.printJson;

public class FindTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("pessoadb");
		MongoCollection<Document> collection = db.getCollection("testePessoas");
		
		collection.drop();
		
		Document pessoa1 = new Document("nome", "Jack Bauer")
				.append("idade", 37)
				.append("profissao", "Ator");
		
		Document pessoa2 = new Document("nome", "James Jim")
				.append("idade", 33)
				.append("profissao", "Músico");
		
		Document pessoa3 = new Document("nome", "Rusty Foster")
				.append("idade", 50)
				.append("profissao", "Policial");
		
		Document pessoa4 = new Document("nome", "Michelle Rodriguez")
				.append("idade", 30)
				.append("profissao", "Atriz");
		
		Document pessoa5 = new Document("nome", "Lil Wayne")
				.append("idade", 32)
				.append("profissao", "Cantor");
		
		collection.insertMany(Arrays.asList(pessoa1, pessoa2 , pessoa3, pessoa4, pessoa5));
		
		System.out.println("Find One");
		Document first = collection.find().first();
		printJson(first);

		System.out.println("Find all with into");
		List<Document> all = collection.find().into(new ArrayList<Document>());
		for(Document doc : all) {
			printJson(doc);
		}
		
		System.out.println("Find all with iteration");
		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while(cursor.hasNext()) {
				Document doc = cursor.next();
				printJson(doc);
			}
		} finally {
			cursor.close();
		}
		
		System.out.println("Count");
		long count = collection.count();
		System.out.println(count);
	
	}

}
