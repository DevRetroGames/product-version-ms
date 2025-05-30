package com.productversion.productversionms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.productversion.productversionms.model.EstadoVersion;

import lombok.Data;

@Data
public class VersionDto {
	private UUID id;
    private UUID productoId;
    private String numeroVersion;
    private LocalDate fechaLanzamiento;
    private EstadoVersion estado;
    private String notas;
    private LocalDateTime fechaCreacion;
}