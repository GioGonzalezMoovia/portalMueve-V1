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
@Table(name="admin_roles_modulos")
@Data
public class RolesModulosModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name = "roles_id")
	private RolesModel rolesId;
	
	@ManyToOne()
	@JoinColumn(name = "modulos_id")
	private ModulosModel modulosId;

	public RolesModulosModel(RolesModel rolesId, ModulosModel modulosId) {
	
		this.rolesId = rolesId;
		this.modulosId = modulosId; 
	}

	public RolesModulosModel() {
	}
	

	
	
}
