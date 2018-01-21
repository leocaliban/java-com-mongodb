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
import com.mongodb.client.model.Sorts;

public class FindOrdenadoLimitTest {

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
				
		//projeção para esconder a idade e o id da consulta json
		Bson projecao = Projections.fields(Projections.include("nome", "idade"),Projections.excludeId());
		
		//Ordenar por idade
		Bson sort = new Document("idade", 1);
		//ordenar com MongoDB
		//Bson sort = Sorts.ascending("idade");
		//Bson sort = Sorts.descending("idade");
				
		List<Document> all = collection.find()
				.projection(projecao)
				.sort(sort)
				//.limit(5) indica um limite de documentos para a consulta
				//.skip(5) indica que os documentos printados serão a partir do quinto.
				.into(new ArrayList<Document>());
		for(Document doc : all) {
			printJson(doc);
		}

	}

}
