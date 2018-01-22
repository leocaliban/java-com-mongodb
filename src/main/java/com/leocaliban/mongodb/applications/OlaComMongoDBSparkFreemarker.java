package com.leocaliban.mongodb.applications;

import java.io.StringWriter;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class OlaComMongoDBSparkFreemarker {

	public static void main(String[] args) {
		//configuração do freemarker
		Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(OlaComMongoDBSparkFreemarker.class, "/");
		
		//configuraçãp do mongodb
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("teste");
		MongoCollection<Document> collection = db.getCollection("hello");
		
		collection.drop();
		
		Document pessoa = new Document("nome", "Jack Bauer")
				.append("idade", 37)
				.append("profissao", "Ator");
		
		collection.insertOne(pessoa);
		
		Spark.get("/", new Route() {

			@Override
			public Object handle(Request request, Response response){
				StringWriter writer = new StringWriter();
				try {
					Template olaTemplate = configuration.getTemplate("ola.ftl");
					
					Document document = collection.find().first();
					olaTemplate.process(document, writer);
				}
				catch(Exception e) {
					Spark.halt(500);
					e.printStackTrace();
				}
				return writer;
			}
		});
	}
}
