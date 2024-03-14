package com.moovia.portalMueve.dto;

import lombok.Data;

@Data
public class SucursalDto {
	private Long id;
	private String nombre;

	public SucursalDto(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
}