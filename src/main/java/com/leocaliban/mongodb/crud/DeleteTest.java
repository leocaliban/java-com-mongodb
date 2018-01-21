package com.leocaliban.mongodb.crud;

import static com.leocaliban.util.Helpers.printJson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class DeleteTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("pessoadb");
		MongoCollection<Document> collection = db.getCollection("pessoasComId");
		
		collection.drop();
		
		Document pessoa1 = new Document("nome", "Jack Bauer")
				.append("idade", 37)
				.append("profissao", "Ator")
				.append("_id", 1);
		
		Document pessoa2 = new Document("nome", "James Jim")
				.append("idade", 33)
				.append("profissao", "Músico")
				.append("_id", 2);
		
		Document pessoa3 = new Document("nome", "Rusty Foster")
				.append("idade", 50)
				.append("profissao", "Policial")
				.append("_id", 3);
		
		Document pessoa4 = new Document("nome", "Michelle Rodriguez")
				.append("idade", 30)
				.append("profissao", "Ator")
				.append("_id", 4);
		
		Document pessoa5 = new Document("nome", "Lil Wayne")
				.append("idade", 32)
				.append("profissao", "Cantor")
				.append("_id", 5);
		
		Document pessoa6 = new Document("nome", "Lilly James")
				.append("idade", 27)
				.append("profissao", "Ator")
				.append("_id", 6);
		
		collection.insertMany(Arrays.asList(pessoa1, pessoa2 , pessoa3, pessoa4, pessoa5, pessoa6));
		
		Bson filtro = Filters.eq("_id", 3);
		
		//Deletar 1 pessoa com id 3 do filtro
		collection.deleteOne(filtro);
		
		List<Document> all = collection.find()
				.into(new ArrayList<Document>());
		for(Document doc : all) {
			printJson(doc);
		}
		System.out.println("FIM do deleteOne");
		//filtrar idade maior que 30
		Bson filtro2 = Filters.gt("idade", 30);
		
		//deleteMany
		collection.deleteMany(filtro2);		
		
		all = collection.find()
				.into(new ArrayList<Document>());
		for(Document doc : all) {
			printJson(doc);
		}

	}

}
