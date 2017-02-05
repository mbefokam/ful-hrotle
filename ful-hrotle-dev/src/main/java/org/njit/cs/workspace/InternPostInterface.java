package org.njit.cs.workspace;

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


@Path("/resource")
public interface InternPostInterface {
	

	@Path("/interpath/{format}")
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	Response producejson(InputStream json, @PathParam("format") String format) throws IOException,MediaTypeException;
	
	@Path("/interpath/{condition}/{age}/{weight}/{location}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	//@Produces({MediaType.TEXT_PLAIN})
	Response getSuggestions(@PathParam("condition") String condition, @PathParam("age") String age,
							@PathParam("weight") String weight, @PathParam("location") String location);
}
