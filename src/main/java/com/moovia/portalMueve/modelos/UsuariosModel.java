package com.moovia.portalMueve.modelos;


import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="admin_usuarios")
@Data
public class UsuariosModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String correo;
	private String nombre;
	private String apellido;
	private String Password;
	private String token;
	private Date fecha;
	private String codigo;
	private Long usuarioId;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "estados_id")
	private EstadosModel estadosId;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "roles_id")
	private RolesModel rolesId;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "sucursal_id")
	private SucursalModel sucursalId;

	public UsuariosModel(String correo, String nombre, String apellido, String password, String token, Date fecha, String codigo,
			EstadosModel estadosId, RolesModel rolesId, SucursalModel sucursalId) {
		this.correo = correo;
		this.nombre = nombre;
		this.apellido = apellido;
		Password = password;
		this.token = token;
		this.fecha = fecha;
		this.codigo = codigo;
		this.estadosId = estadosId;
		this.rolesId = rolesId;
		this.sucursalId = sucursalId;
	}

	public UsuariosModel() {
	}

}