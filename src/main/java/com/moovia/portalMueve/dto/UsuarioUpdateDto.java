package com.moovia.portalMueve.dto;

import lombok.Data;

@Data
public class UsuarioUpdateDto {
	
	private String nombre;
	private String apellido;
	private String correo;
	private String password;
	private Long rol_id;
	
}
