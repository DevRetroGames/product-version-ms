package com.productversion.productversionms.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.productversion.productversionms.dto.ApiResponse;
import com.productversion.productversionms.dto.PageDto;
import com.productversion.productversionms.dto.ProductoDto;
import com.productversion.productversionms.dto.VersionDto;

@Service
public interface IProductoService {
	
	public Page<ProductoDto> listProducts(PageDto pageDto);
	
	public ProductoDto createProduct(ProductoDto productoDto);
	
	public Page<ProductoDto> getProduct(UUID id);
	
	public Page<ProductoDto> updateProduct(UUID id, ProductoDto productoDto);
	
	public ApiResponse deleteProduct(UUID id);
	
	public Page<VersionDto> listVersions(UUID id, PageDto pageDto);
	
	public Page<VersionDto> createVersions(UUID id, VersionDto versionDto); 
}
