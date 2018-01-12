package com.leocaliban.mongodb.views;

import spark.Spark;

public class OlaComSpark {

	public static void main(String[] args) {
		Spark.get("/", (request,response) -> "Olá SPARK!!!"); {
		}
	}
}
