/**
 * Created by Apache CXF WadlToJava code generator
 **/
package org.njit.cs.rest.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.njit.cs.exception.MediaTypeException;

import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/v1.0/rules")
public interface UserAccount {
	

	@Path("/intern")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActivities(InputStream json) throws IOException;

	@Path("/{Customer_ID}/{format}")
	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	Response produceUsername(InputStream json, @PathParam("format") String format) throws IOException,MediaTypeException;
}