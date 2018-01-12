package com.leocaliban.mongodb.views;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class OlaComFreemaker {

	public static void main(String[] args) {
		@SuppressWarnings("deprecation")
		Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(OlaComFreemaker.class, "/");
		
		try {
		Template olaTemplate = configuration.getTemplate("ola.ftl");
		StringWriter writer = new StringWriter();
		Map<String, Object> olaMap = new HashMap<String, Object>();
		olaMap.put("nome", "com Freemarker");
		
		olaTemplate.process(olaMap, writer);
		
		System.out.println(writer);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
