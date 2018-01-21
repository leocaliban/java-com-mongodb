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
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

public class UpdateReplaceTest {

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
		
		//trocar updateOne por updateMany para atualizar vários documentos
		collection.updateOne(filtro, Updates.combine(Updates.set("idade", 53), Updates.set("profissao", "Tira")),
				new UpdateOptions().upsert(true)); //upsert true - se não for encontrado o documento ele cria um

		List<Document> all = collection.find()
				.into(new ArrayList<Document>());
		for(Document doc : all) {
			printJson(doc);
		}
	}
}
