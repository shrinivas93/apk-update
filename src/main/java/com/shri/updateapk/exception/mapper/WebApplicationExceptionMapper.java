package com.shri.updateapk.exception.mapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.shri.updateapk.service.ResponseService;

@Provider
public class WebApplicationExceptionMapper implements
		ExceptionMapper<WebApplicationException> {

	@Autowired
	ResponseService responseService;

	private static final Logger LOGGER = LogManager
			.getLogger(WebApplicationExceptionMapper.class.getName());

	@Override
	public Response toResponse(WebApplicationException exception) {
		LOGGER.error(exception);
		return responseService.getErrorResponse(exception.getMessage(),
				exception.getResponse().getStatus());
	}

}
