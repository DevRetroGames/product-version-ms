package com.productversion.productversionms.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.productversion.productversionms.dto.VersionDto;
import com.productversion.productversionms.exceptions.CustomNotFoundException;
import com.productversion.productversionms.model.Version;
import com.productversion.productversionms.repository.IVersionRepository;
import com.productversion.productversionms.service.IVersionService;
import com.productversion.productversionms.util.MapperUtil;

@Service
@Qualifier("/v1/VersionServiceImpl")
public class VersionServiceImpl implements IVersionService {
	
	@Autowired
	private IVersionRepository versionRepository;
	
	@Autowired
	private MapperUtil mapperUtil;

	@Override
	public Page<VersionDto> getVersions(UUID id) {
		VersionDto versionDto = 
				this.versionRepository
				.findById(id)
				.map(this.mapperUtil::versionEntityToVersionDto)
				.orElseThrow(() -> new CustomNotFoundException("Versión no encontrado"));
		return new PageImpl<>(List.of(versionDto));
	}

	@Override
	public Page<VersionDto> updateVersion(UUID id, VersionDto versionDto) {
		Version versionSearch = 
				this.versionRepository
					.findById(id)
					.orElseThrow(() -> new CustomNotFoundException("Versión no encontrado"));
		Version version = this.mapperUtil.versionDtoToVersionEntity(versionDto);
		Version versionSave = this.versionRepository.save(version);
		VersionDto versionDtoSave = this.mapperUtil.versionEntityToVersionDto(versionSave);
		return new PageImpl<>(List.of(versionDtoSave));
	}

	@Override
	public Page<VersionDto> deleteVersion(UUID id) {
		Version versionSearch = 
				this.versionRepository
					.findById(id)
					.orElseThrow(() -> new CustomNotFoundException("Versión no encontrado"));
		this.versionRepository.deleteById(id);
		return new PageImpl<>(List.of(null));
	}

}
