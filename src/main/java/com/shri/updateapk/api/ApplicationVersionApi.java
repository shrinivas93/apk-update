package com.shri.updateapk.api;

import java.io.File;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shri.updateapk.service.ApplicationVersionService;
import com.shri.updateapk.service.ResponseService;

@Component
@Path("apk")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ApplicationVersionApi {

	@Autowired
	private ResponseService responseService;

	@Autowired
	private ApplicationVersionService applicationVersionService;

	@GET
	public Response getApplicationVersions() {
		return responseService.getSuccessResponse(applicationVersionService.getApplicationVersions(),
				"Version details of the application", Status.OK.getStatusCode());
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response addApplicationVersion(@FormDataParam("versionNumber") String versionNumber,
			@FormDataParam("changeLog") String changeLog, @FormDataParam("apkFile") InputStream apkInputStream,
			@FormDataParam("apkFile") FormDataContentDisposition apkData) {
		return responseService.getSuccessResponse(
				applicationVersionService.addApplicationVersion(versionNumber, changeLog, apkInputStream, apkData),
				"Application version added successfully", Status.OK.getStatusCode());
	}

	@GET
	@Path("latest")
	public Response getLatestApplicationVersion() {
		return responseService.getSuccessResponse(applicationVersionService.getLatestApplicationVersion(),
				"Latest Version details of the application", Status.OK.getStatusCode());
	}

	@GET
	@Path("{versionNumber}/download")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadApkByVersionNumber(@PathParam("versionNumber") String versionNumber) {
		File apkFile = applicationVersionService.downloadApkByVersionNumber(versionNumber);
		return Response.ok(apkFile).header("Content-Disposition", "attachment; filename='" + apkFile.getName() + "'")
				.build();
	}

}
