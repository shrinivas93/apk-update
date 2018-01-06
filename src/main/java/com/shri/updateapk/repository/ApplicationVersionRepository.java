package com.shri.updateapk.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shri.updateapk.document.ApplicationVersion;

public interface ApplicationVersionRepository extends MongoRepository<ApplicationVersion, String> {

	ApplicationVersion findFirstByOrderByReleaseDateDesc();

	ApplicationVersion findByVersionNumber(String versionNumber);

}
