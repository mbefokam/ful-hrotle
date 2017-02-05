package org.njit.cs.workspace;

import java.io.IOException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.njit.cs.exception.MediaTypeException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;

public class InternPost implements InternPostInterface {


	@Override
	public Response producejson(InputStream message,
			@PathParam("format") String format) throws IOException,
			MediaTypeException {
		
		if (format.equals("json")) {
			return Response.status(Status.CREATED)
					.type(MediaType.APPLICATION_JSON)
					.entity(jsonParser(message)).build();
		}

		else {
			throw new MediaTypeException("MediaType Not Supported");
		}

	}
	
	public String jsonParser(InputStream json) throws IOException {
		JsonReader jRead = Json.createReader(json);
		JsonStructure jStruct = jRead.read();
		jRead.close();
		json.close();
		return jStruct.toString();

	}

	public Response getSuggestions(String condition, String age, String weight, String location){
		return Response.status(Status.ACCEPTED)
						.type(MediaType.APPLICATION_JSON)
						//.type(MediaType.TEXT_PLAIN)
						.header("Cache-Control", "no-cache")
						.header("Access-Control-Allow-Origin", "*")
						.entity(Activities.determineActivities(condition, location))
						.build();
	}
}
