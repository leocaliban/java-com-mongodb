package com.leocaliban.mongodb.views;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class OlaComSparkEFreemaker {
	public static void main(String[] args) {
		@SuppressWarnings("deprecation")
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(OlaComFreemaker.class, "/");
		
		Spark.get("/", new Route() {

			@Override
			public Object handle(final Request arg0, final Response arg1){
				StringWriter writer = new StringWriter();
				try {
					Template olaTemplate = configuration.getTemplate("ola.ftl");
					Map<String, Object> olaMap = new HashMap<String, Object>();
					olaMap.put("nome", "com Freemarker e Spark");
					
					olaTemplate.process(olaMap, writer);
					
					}catch (Exception e) {
						e.printStackTrace();
					}
				return writer;
			}
			
		});
	}

}
