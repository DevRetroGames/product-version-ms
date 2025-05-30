package com.productversion.productversionms.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.productversion.productversionms.dto.VersionDto;
import com.productversion.productversionms.service.IVersionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/versiones")
public class VersionController {
	
	@Autowired
	@Qualifier("/v1/VersionServiceImpl")
	private IVersionService versionService;

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Page<VersionDto> getVersions(@Valid @PathVariable UUID id) {
		return this.versionService.getVersions(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Page<VersionDto> updateVersion(@Valid @PathVariable UUID id, @Valid @RequestBody VersionDto versionDto) {
		return this.versionService.updateVersion(id, versionDto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Page<VersionDto> deleteVersion(@Valid @PathVariable UUID id) {
		return this.versionService.deleteVersion(id);
	}
	
}
