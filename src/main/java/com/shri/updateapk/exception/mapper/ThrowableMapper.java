package com.shri.updateapk.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.shri.updateapk.service.ResponseService;

@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {

	@Autowired
	ResponseService responseService;

	private static final Logger LOGGER = LogManager
			.getLogger(ThrowableMapper.class.getName());

	@Override
	public Response toResponse(Throwable exception) {
		LOGGER.error(exception);
		return responseService.getErrorResponse(exception.getMessage(),
				Status.INTERNAL_SERVER_ERROR.getStatusCode());
	}

}
