package com.shri.updateapk.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shri.updateapk.document.ApplicationVersion;
import com.shri.updateapk.repository.ApplicationVersionRepository;
import com.shri.updateapk.service.ApplicationVersionService;

@Service
public class ApplicationVersionServiceImpl implements ApplicationVersionService {

	@Autowired
	private ApplicationVersionRepository applicationVersionRepository;

	@Value("${apk.base.path}")
	private String apkBasePath;

	@Override
	public ApplicationVersion getLatestApplicationVersion() {
		return applicationVersionRepository.findFirstByOrderByReleaseDateDesc();
	}

	@Override
	public List<ApplicationVersion> getApplicationVersions() {
		return applicationVersionRepository.findAll();
	}

	@Override
	public ApplicationVersion addApplicationVersion(String versionNumber, String changeLog, InputStream apkInputStream,
			FormDataContentDisposition apkData) {
		if (StringUtils.isEmpty(versionNumber)) {
			throw new WebApplicationException("Invalid Version Number", Status.BAD_REQUEST);
		}
		if (apkInputStream == null) {
			throw new WebApplicationException("File missing", Status.BAD_REQUEST);
		}
		ApplicationVersion existingApplicationVersion = applicationVersionRepository.findByVersionNumber(versionNumber);
		if (existingApplicationVersion != null) {
			throw new WebApplicationException("Application with this version already exists", Status.CONFLICT);
		}
		Date currentTime = new Date();
		ApplicationVersion applicationVersion = new ApplicationVersion();
		applicationVersion.setVersionNumber(versionNumber);
		applicationVersion.setChangeLog(changeLog);
		applicationVersion.setReleaseDate(currentTime);
		File apkBaseDirectory = new File(apkBasePath);
		if (!apkBaseDirectory.exists()) {
			apkBaseDirectory.mkdirs();
		}
		String filePath = apkBasePath.concat(versionNumber).concat(".apk");
		try (FileOutputStream fos = new FileOutputStream(filePath)) {
			int read;
			byte[] data = new byte[128 * 1024];
			while ((read = apkInputStream.read(data)) != -1) {
				fos.write(data, 0, read);
			}
		} catch (IOException e) {
			throw new WebApplicationException("Unable to upload APK", Status.INTERNAL_SERVER_ERROR);
		}
		return applicationVersionRepository.save(applicationVersion);
	}

	@Override
	public File downloadApkByVersionNumber(String versionNumber) {
		File apkFile = new File(apkBasePath.concat(versionNumber).concat(".apk"));
		if (!apkFile.exists()) {
			throw new WebApplicationException("Application with this version does not exist", Status.CONFLICT);
		}
		return apkFile;
	}

}
