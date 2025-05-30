package com.productversion.productversionms.serviceImpl;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.productversion.productversionms.dto.ApiResponse;
import com.productversion.productversionms.dto.PageDto;
import com.productversion.productversionms.dto.ProductoDto;
import com.productversion.productversionms.dto.VersionDto;
import com.productversion.productversionms.exceptions.CustomNotFoundException;
import com.productversion.productversionms.model.Producto;
import com.productversion.productversionms.model.Version;
import com.productversion.productversionms.repository.IProductoRepository;
import com.productversion.productversionms.repository.IVersionRepository;
import com.productversion.productversionms.service.IProductoService;
import com.productversion.productversionms.util.MapperUtil;

@Service
@Qualifier("/v1/ProductoServiceImpl")
public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	private IProductoRepository productoRepository;
	
	@Autowired
	private IVersionRepository versionRepository;
	
	@Autowired
	private MapperUtil mapperUtil;

	@Override
	public Page<ProductoDto> listProducts(PageDto pageDto) {
		PageRequest pageRequest = PageRequest.of(pageDto.getPage(), pageDto.getQuantityRecords());
		List<ProductoDto> listProductoDto = 
				this.productoRepository
					.findAll(pageRequest)
					.stream()
					.map(this.mapperUtil::productoEntityToProductoDto)
					.collect(Collectors.toList());
		return new PageImpl<>(listProductoDto);
	}

	@Override
	public ProductoDto createProduct(ProductoDto productoDto) {
		Producto producto = this.mapperUtil.productoDtoToProductoEntity(productoDto);
		Producto productoSave = this.productoRepository.save(producto);
		return this.mapperUtil.productoEntityToProductoDto(productoSave);
	}

	@Override
	public Page<ProductoDto> getProduct(UUID id) {
		ProductoDto productoDto = 
				this.productoRepository
					.findById(id)
					.map(this.mapperUtil::productoEntityToProductoDto)
					.orElseThrow(() -> new CustomNotFoundException("Registro no encontrado"));
		return new PageImpl<>(List.of(productoDto));
	}

	@Override
	public Page<ProductoDto> updateProduct(UUID id, ProductoDto productoDto) {
		Producto productoSearch = 
				this.productoRepository
					.findById(id)
					.orElseThrow(() -> new CustomNotFoundException("Registro no encontrado"));
		Producto producto = this.mapperUtil.productoDtoToProductoEntity(productoDto);
		Producto productoSave = this.productoRepository.save(producto);
		ProductoDto productoDtoSave = this.mapperUtil.productoEntityToProductoDto(productoSave);
		return new PageImpl<>(List.of(productoDtoSave));
	}

	@Override
	public ApiResponse deleteProduct(UUID id) {
		Producto productoSearch = 
				this.productoRepository
					.findById(id)
					.orElseThrow(() -> new CustomNotFoundException("Registro no encontrado"));
		OffsetDateTime now = OffsetDateTime.now();
		int rowsAffected = this.productoRepository.actualizarEliminadoEn(id, now);
		if (rowsAffected == 0) {
	        throw new CustomNotFoundException("Producto no encontrado");
	    }
		return new ApiResponse("Producto eliminado correctamente.");
	}

	@Override
	public Page<VersionDto> listVersions(UUID id, PageDto pageDto) {
		PageRequest pageRequest = PageRequest.of(pageDto.getPage(), pageDto.getQuantityRecords());
		Page<Version> versiones = 
				this.versionRepository
				.findByProductoId(id, pageRequest);
		if(versiones.isEmpty() || !versiones.hasContent()) {
			throw new CustomNotFoundException("Producto no encontrado");
		}
		return versiones.map(this.mapperUtil::versionEntityToVersionDto);
	}

	@Override
	public Page<VersionDto> createVersions(UUID id, VersionDto versionDto) {
		Producto producto = 
				this.productoRepository
					.findById(id)
					.orElseThrow(() -> new CustomNotFoundException("Producto no encontrado"));
		Version version = this.mapperUtil.versionDtoToVersionEntity(versionDto);
		version.setProducto(producto);
		Version versionSave = this.versionRepository.save(version);
		VersionDto versionDtoSave = this.mapperUtil.versionEntityToVersionDto(versionSave);
		return new PageImpl<>(List.of(versionDtoSave));
	}

}
