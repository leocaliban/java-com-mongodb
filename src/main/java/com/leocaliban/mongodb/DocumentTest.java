package com.leocaliban.mongodb;

import java.util.Arrays;
import java.util.Date;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.leocaliban.util.Helpers.printJson;

public class DocumentTest {

	public static void main(String[] args) {
		Document document = new Document()
                .append("String", "MongoDB, Hello")
                .append("int", 42)
                .append("long", 1L)
                .append("double", 1.1)
                .append("boolean", false)
                .append("date", new Date())
                .append("objectId", new ObjectId())
                .append("null", null)
                .append("embeddedDoc", new Document("x", 0))
                .append("list", Arrays.asList(1, 2, 3));

		printJson(document);
		
		BsonDocument bsonDocument = new BsonDocument("str", new BsonString("MongoDB, Hello"));

	}

}
