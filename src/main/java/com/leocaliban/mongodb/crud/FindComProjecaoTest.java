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
import com.mongodb.client.model.Projections;

public class FindComProjecaoTest {

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
		
		Bson filtro = new Document("profissao", "Ator");
		
		//projeção para esconder a idade e o id da consulta json
		Bson projecao = new Document("idade", 0).append("_id", 0);
		
		//projeção com o mongoDB EXCLUDE
		//Bson projecao = Projections.exclude("idade");
		//projeção com o mongoDB INCLUDE
		//Bson projecao = Projections.include("nome");
		//projeção com o mongoDB EXCLUDE + INCLUDE 
		//Bson projecao = Projections.fields(Projections.include("nome"),Projections.exclude("_id");
		
		List<Document> all = collection.find(filtro)
				.projection(projecao)
				.into(new ArrayList<Document>());
		for(Document doc : all) {
			printJson(doc);
		}
	}
}
