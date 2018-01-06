package com.shri.updateapk.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.shri.updateapk.document.ApplicationVersion;

public interface ApplicationVersionService {

	ApplicationVersion getLatestApplicationVersion();

	List<ApplicationVersion> getApplicationVersions();

	ApplicationVersion addApplicationVersion(String versionNumber, String changeLog, InputStream apkInputStream,
			FormDataContentDisposition apkData);

	File downloadApkByVersionNumber(String versionNumber);

}
