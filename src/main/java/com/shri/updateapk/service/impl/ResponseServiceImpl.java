package com.shri.updateapk.service.impl;

import java.io.IOException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shri.updateapk.pojo.ResponseEntity;
import com.shri.updateapk.pojo.ResponseStatus;
import com.shri.updateapk.pojo.ResponseType;
import com.shri.updateapk.service.ResponseService;

@Service
public class ResponseServiceImpl implements ResponseService {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public Response getSuccessResponse(String message, int code) {
		return getSuccessResponse(null, message, code);
	}

	@Override
	public Response getSuccessResponse(Object object, String message, int code) {
		ResponseStatus status = new ResponseStatus(ResponseType.SUCCESS, code, message);
		ResponseEntity responseEntity = new ResponseEntity(object, status, null);
		String response = null;
		try {
			response = objectMapper.writeValueAsString(responseEntity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(code).type(MediaType.APPLICATION_JSON).entity(response).build();
	}

	@Override
	public Response getErrorResponse(String message, int code) {
		return getErrorResponse(null, message, code);
	}

	@Override
	public Response getErrorResponse(Object object, String message, int code) {
		ResponseStatus status = new ResponseStatus(ResponseType.ERROR, code, message);
		ResponseEntity responseEntity = new ResponseEntity(object, status, null);
		String response = null;
		try {
			response = objectMapper.writeValueAsString(responseEntity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(code).type(MediaType.APPLICATION_JSON).entity(response).build();
	}

}
