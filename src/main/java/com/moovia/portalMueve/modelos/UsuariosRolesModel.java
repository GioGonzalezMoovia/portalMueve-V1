package com.moovia.portalMueve.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="admin_usuarios_roles")
@Data
public class UsuariosRolesModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name = "usuarios_id")
	private UsuariosModel usuariosId;
	
	@ManyToOne()
	@JoinColumn(name = "roles_id")
	private RolesModel rolesId;
	
	@ManyToOne()
	@JoinColumn(name = "estados_id")
	private EstadosModel estadosId; 
	
	public UsuariosRolesModel() {
	}

	public UsuariosRolesModel(UsuariosModel usuariosId, RolesModel rolesId, EstadosModel estadosId) {
		super();
		this.usuariosId = usuariosId;
		this.rolesId = rolesId;
		this.estadosId = estadosId;
	}
}