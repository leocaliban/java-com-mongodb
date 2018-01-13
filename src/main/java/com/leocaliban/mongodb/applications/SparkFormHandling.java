package com.leocaliban.mongodb.applications;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkFormHandling {

	public static void main(String[] args) {
		//Configura o Freemarker
		@SuppressWarnings("deprecation")
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");
		
		//Configura as rotas
		//Rota inicial
		Spark.get("/", new Route() {

			@Override
			public Object handle(final Request request, final Response response) {
				StringWriter writer = new StringWriter();
				try {
					Map<String, Object> coresMap = new HashMap<String, Object>();
					coresMap.put("cores", Arrays.asList("Azul","Preto","Branco","Verde","Vermelho"));
					
					Template corTemplate = configuration.getTemplate("cor.ftl");
					
					corTemplate.process(coresMap, writer);
				}
				catch(Exception e) {
					return null;
				}
				return writer;
			}
			
		});
		//Rota após submeter o formulário
		Spark.post("/cor-favorita", new Route() {

			@Override
			public Object handle(final Request request, final Response response) {
				final String cor = request.queryParams("cor");
				if(cor == null) {
					return "Você deve escolher uma cor :)";
				}
				else {
					return "Sua cor favorita é " + cor;
				}
			}
		});
	}
}
