package com.productversion.productversionms.repository;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.productversion.productversionms.model.Producto;

import jakarta.transaction.Transactional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, UUID> {

	@Modifying
    @Transactional
    @Query("UPDATE Producto p SET p.eliminadoEn = :fecha WHERE p.id = :id")
    int actualizarEliminadoEn(@Param("id") UUID id, @Param("fecha") OffsetDateTime fecha);
	
}
