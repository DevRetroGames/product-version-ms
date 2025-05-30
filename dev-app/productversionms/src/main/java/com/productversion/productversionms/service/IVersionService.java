package com.productversion.productversionms.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.productversion.productversionms.dto.VersionDto;

@Service
public interface IVersionService {

	public Page<VersionDto> getVersions(UUID id);
	
	public Page<VersionDto> updateVersion(UUID id, VersionDto versionDto);
	
	public Page<VersionDto> deleteVersion(UUID id);
	
}
