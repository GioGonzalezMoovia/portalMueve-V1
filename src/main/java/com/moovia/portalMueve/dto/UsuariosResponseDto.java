package com.moovia.portalMueve.dto;

import com.moovia.portalMueve.modelos.EstadosModel;
import com.moovia.portalMueve.modelos.ModulosModel;
import com.moovia.portalMueve.modelos.RolesModel;
import com.moovia.portalMueve.modelos.SucursalModel;

import lombok.Data;

@Data
public class UsuariosResponseDto {
	private Long id;
	private String nombre;
	private String apellido;
	private String correo;
	private RolesModel RolesId;
	private String Rol;
	private EstadosModel estadoId;
	private SucursalModel sucursalId;
	private String codigo;

	public UsuariosResponseDto(Long id, String nombre, String apellido, String correo, String codigo,
			RolesModel rolesId, String rol, EstadosModel estadoId, SucursalModel sucursalId) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.codigo = codigo;
		RolesId = rolesId;
		Rol = rol;
		this.estadoId = estadoId;
		this.sucursalId = sucursalId;
	}

}