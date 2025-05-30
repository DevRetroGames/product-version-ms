package com.productversion.productversionms.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.productversion.productversionms.dto.ApiResponse;
import com.productversion.productversionms.dto.PageDto;
import com.productversion.productversionms.dto.ProductoDto;
import com.productversion.productversionms.dto.VersionDto;
import com.productversion.productversionms.service.IProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
	
	@Autowired
	@Qualifier("/v1/ProductoServiceImpl")
	private IProductoService productoService;
	
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public Page<ProductoDto> listProducts(@Valid @RequestBody PageDto pageDto) {
		return this.productoService.listProducts(pageDto);
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductoDto createProduct(@Valid @RequestBody ProductoDto productoDto) {
		return this.productoService.createProduct(productoDto);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Page<ProductoDto> getProduct(@Valid @PathVariable UUID id) {
		return this.productoService.getProduct(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Page<ProductoDto> updateProduct(@Valid @PathVariable UUID id, @Valid @RequestBody ProductoDto productoDto) {
		return this.productoService.updateProduct(id, productoDto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ApiResponse deleteProduct(@Valid @PathVariable UUID id) {
		return this.productoService.deleteProduct(id);
	}
	
	@GetMapping("/{id}/versiones")
	@ResponseStatus(HttpStatus.OK)
	public Page<VersionDto> listVersions(@Valid @PathVariable UUID id, @Valid @RequestBody PageDto pageDto) {
		return this.productoService.listVersions(id, pageDto);
	}
	
	@PostMapping("/{id}/versiones")
	@ResponseStatus(HttpStatus.CREATED)
	public Page<VersionDto> createVersions(@Valid @PathVariable UUID id, @Valid @RequestBody VersionDto versionDto) {
		return this.productoService.createVersions(id, versionDto);
	}

}




















