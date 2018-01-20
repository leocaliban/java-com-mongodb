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

//Importar métodos estáticos
import static com.mongodb.client.model.Filters.*;

public class FindComFiltroTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("pessoadb");
		MongoCollection<Document> collection = db.getCollection("testeComFiltro");
		
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
				.append("profissao", "Ator");
		
		Document pessoa5 = new Document("nome", "Lil Wayne")
				.append("idade", 32)
				.append("profissao", "Cantor");
		
		Document pessoa6 = new Document("nome", "Lilly James")
				.append("idade", 27)
				.append("profissao", "Ator");
		
		collection.insertMany(Arrays.asList(pessoa1, pessoa2 , pessoa3, pessoa4, pessoa5, pessoa6));
		
		//todos os atores
		Bson filtro1 = new Document("profissao", "Ator");
		//atores que tenham mais de 30 anos
		Bson filtro2 = new Document("profissao", "Ator").append("idade", new Document("$gte", 30));
		//atores que tenham entre 30 e 35 anos
		Bson filtro3 = new Document("profissao", "Ator").append("idade", new Document("$gte", 30).append("$lt", 35));
		
		//filtro do mongoDB
		//Bson filtroMongo = Filters.eq("profissao", "Ator");
		//Bson filtroMongo2 = Filters.and(eq("profissao", "Ator"), gt("idade", 30));
		
		
		System.out.println("Busca por Atores");
		List<Document> all = collection.find(filtro1).into(new ArrayList<Document>());
		for(Document doc : all) {
			printJson(doc);
		}
		long count = collection.count(filtro1);
		System.out.println(count);
		
		System.out.println("Busca por Atores com mais de 30 anos");
		List<Document> all2 = collection.find(filtro2).into(new ArrayList<Document>());
		for(Document doc : all2) {
			printJson(doc);
		}
		long count2 = collection.count(filtro2);
		System.out.println(count2);
		
		System.out.println("Busca por Atores entre 30 e 35 anos");
		List<Document> all3 = collection.find(filtro3).into(new ArrayList<Document>());
		for(Document doc : all3) {
			printJson(doc);
		}
		long count3 = collection.count(filtro3);
		System.out.println(count3);

	}

}
