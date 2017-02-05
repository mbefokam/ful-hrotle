package org.njit.cs.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class MediaTypeExceptionMapper implements ExceptionMapper<MediaTypeException> {

	@Override
	public Response toResponse(MediaTypeException mte) {
		// TODO Auto-generated method stub
		return Response.status(Status.BAD_REQUEST).entity(mte.getMessage()).build();
	}

}
