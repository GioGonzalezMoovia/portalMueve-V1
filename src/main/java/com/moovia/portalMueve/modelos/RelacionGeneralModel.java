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
@Table(name = "admin_relacion_general")
@Data
public class RelacionGeneralModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "usuarios_roles_id")
	private UsuariosRolesModel usuariosRolesId;

	@ManyToOne()
	@JoinColumn(name = "empresa_id")
	private EmpresaModel empresaId;

	@ManyToOne()
	@JoinColumn(name = "estados_id")
	private EstadosModel estadosId;

	@ManyToOne()
	@JoinColumn(name = "usuarios_id")
	private UsuariosModel usuariosId;

	@ManyToOne()
	@JoinColumn(name = "modulos_id")
	private ModulosModel modulosId;

	public RelacionGeneralModel(UsuariosRolesModel usuariosRolesId, EmpresaModel empresaId, EstadosModel estadosId,
			ModulosModel modulosId) {
		this.usuariosRolesId = usuariosRolesId;
		this.empresaId = empresaId;
		this.estadosId = estadosId;
		this.modulosId = modulosId;
	}

	public RelacionGeneralModel() {
	}
}