package com.leocaliban.mongodb.applications;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class OlaComSpark {

	public static void main(String[] args) {
		Spark.get("/", new Route() {

			@Override
			public Object handle(Request request, Response response){
				return "Página inicial";
			}
			
		});
		
		Spark.get("/ola", new Route() {

			@Override
			public Object handle(Request request, Response response){
				return "Olá com SPARK!!!";
			}
			
		});
		
		//-thing será o retorno da requisição que será atribuido na url
		Spark.get("/echo/:thing", new Route() {

			@Override
			public Object handle(Request request, Response response){
				return request.params(":thing");
			}
			
		});
	}
	
	
}
