/**
 * Created by Apache CXF WadlToJava code generator
 **/
package org.njit.cs.rest.impl;

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

public class UserAccountImpl implements UserAccount {


	
	public Response getActivities(InputStream json) throws JsonProcessingException {
		String response = "";
		char ch;
		
		try {
			while ((ch = (char)json.read()) != -1){
				response += ch;
			}
		} catch (IOException e){
			System.out.println("Reading input stream failed.");
		}
		return Response.status(Status.OK).entity(response).header("Access-Control-Allow-Origin", "*").build();
		//return getUsername();
		//return null;
	}

	/**
	 * Method which checks that the format specified in the request is in xml or
	 * in json. If neither format is specified, a MediaType Exception will be
	 * thrown. This resource shows the usage of the annotation @PathParam.
	 */

	@Override
	public Response produceUsername(InputStream message,
			@PathParam("format") String format) throws IOException,
			MediaTypeException {

		if (format.equals("xml")) {

			return Response.status(Status.CREATED)
					.type(MediaType.APPLICATION_XML).entity(addXML(message))
					.build();
		} else if (format.equals("json")) {
			return Response.status(Status.CREATED)
					.type(MediaType.APPLICATION_JSON)
					.entity(jsonParser(message)).build();
		}

		else {
			throw new MediaTypeException("MediaType Not Supported");
		}

	}

	/**
	 * Method that reads the json input as Type InputStream and then converts it
	 * to Type String.
	 */
	public String jsonParser(InputStream json) throws IOException {
		JsonReader jRead = Json.createReader(json);
		JsonStructure jStruct = jRead.read();
		jRead.close();
		json.close();
		return jStruct.toString();

	}

	/**
	 * Method that accepts an xml payload and returns the same xml input.
	 */
	public Document addXML(InputStream model) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document ret = null;

		try {
			builder = factory.newDocumentBuilder();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			ret = builder.parse(new InputSource(model));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ret;
	}

}