package com.moovia.portalMueve.dto;

import lombok.Data;

@Data
public class JwtResponseDto {
	private Long id;
	private String nombre; 
	private String perfil; 
	private Long perfil_id;
	private String token;
	public JwtResponseDto(Long id, String nombre, String perfil, Long perfil_id, String token) {
		this.id = id;
		this.nombre = nombre;
		this.perfil = perfil;
		this.perfil_id = perfil_id;
		this.token = token;
	}
	
	
	 
	
}
