package com.productversion.productversionms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.productversion.productversionms.model.EstadoVersionTest;

import lombok.Data;

@Data
public class VersionDtoTest {
	private UUID id;
    private UUID productoId;
    private String numeroVersion;
    private LocalDate fechaLanzamiento;
    private EstadoVersionTest estado;
    private String notas;
    private LocalDateTime fechaCreacion;
}
