package com.moovia.portalMueve.dto;

import lombok.Data;

@Data
public class UsuarioDto {
	private String nombre;
	private String apellido;
	private String correo;
	private String password;
	private String codigoUsuario;
}
