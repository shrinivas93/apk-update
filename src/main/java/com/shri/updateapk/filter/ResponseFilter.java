package com.shri.updateapk.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.shri.updateapk.pojo.Constants;

@Provider
@PreMatching
@Component
public class ResponseFilter implements ContainerResponseFilter {

	private static final Logger LOGGER = LogManager.getLogger(ResponseFilter.class.getName());

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {

		LOGGER.info("Filtering response...");

		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		responseContext.getHeaders().add("Access-Control-Allow-Headers",
				Constants.AUTHORIZATION + ", " + Constants.CONTENT_TYPE);
	}
}
