package com.productversion.productversionms.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.productversion.productversionms.dto.ProductoDto;
import com.productversion.productversionms.dto.VersionDtoTest;
import com.productversion.productversionms.model.Producto;
import com.productversion.productversionms.model.VersionTest;

@ExtendWith(MockitoExtension.class)
public class MapperUtilTest {
	
	@Mock
	private ModelMapper modelMapper;
  
	@InjectMocks
	private MapperUtil mapperUtil;
	
	private Producto producto;
	private ProductoDto productoDto;
	
	private VersionTest versionTest;
	private VersionDtoTest versionDtoTest;
	
	@BeforeEach
	protected void setup() throws Exception {
		
		this.mapperUtil = Mockito.mock(MapperUtil.class);
		
		this.producto = Mockito.mock(Producto.class);
		this.producto.setNombre("test01");
		this.producto.setDescripcion("test01");
		
		this.productoDto = Mockito.mock(ProductoDto.class);
		this.productoDto.setNombre("test01");
		this.productoDto.setDescripcion("test01");
		
		this.versionTest = Mockito.mock(VersionTest.class);
		this.versionTest.setNotas("test01");
		
		this.versionDtoTest = Mockito.mock(VersionDtoTest.class);
		this.versionDtoTest.setNotas("test01");
		
	}
	
	@AfterEach
	protected void tearDown() throws Exception {}
	
	@Test
	void testProductoTestToProductoTest() {
		Mockito
			.when(this.mapperUtil.productoEntityToProductoDto(this.producto))
			.thenReturn(productoDto);
		
		ProductoDto productoDtoTest = this.mapperUtil.productoEntityToProductoDto(producto);
		
		assertEquals(this.productoDto.getNombre(), productoDtoTest.getNombre());
		
		assertEquals(this.productoDto.getDescripcion(), productoDtoTest.getDescripcion());
		
		assertEquals(this.productoDto, productoDtoTest);
	}

}
