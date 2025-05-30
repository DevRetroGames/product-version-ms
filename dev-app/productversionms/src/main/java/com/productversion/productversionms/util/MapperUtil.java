package com.productversion.productversionms.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.productversion.productversionms.dto.ProductoDto;
import com.productversion.productversionms.dto.VersionDto;
import com.productversion.productversionms.model.Producto;
import com.productversion.productversionms.model.Version;

@Component
public class MapperUtil {

	@Autowired
	private ModelMapper modelMapper;
	
	public ProductoDto productoEntityToProductoDto(Producto producto) {
		return this.modelMapper.map(producto, ProductoDto.class);
	}
	
	public Producto productoDtoToProductoEntity(ProductoDto productoDto) {
		return this.modelMapper.map(productoDto, Producto.class);
	}
	
	public VersionDto versionEntityToVersionDto(Version version) {
		return this.modelMapper.map(version, VersionDto.class);
	}
	
	public Version versionDtoToVersionEntity(VersionDto versionDto) {
		return this.modelMapper.map(versionDto, Version.class);
	}
	
}
