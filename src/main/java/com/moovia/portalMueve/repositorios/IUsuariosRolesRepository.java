package com.moovia.portalMueve.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moovia.portalMueve.modelos.EstadosModel;
import com.moovia.portalMueve.modelos.RolesModel;
import com.moovia.portalMueve.modelos.UsuariosModel;
import com.moovia.portalMueve.modelos.UsuariosRolesModel;

public interface IUsuariosRolesRepository extends JpaRepository<UsuariosRolesModel, Long> {
	List<UsuariosRolesModel> findByUsuariosIdAndEstadosId(UsuariosModel usuariosId, EstadosModel estadosId);

	void deleteByUsuariosId(UsuariosModel usuariosId);

	UsuariosRolesModel findByUsuariosIdAndRolesIdAndEstadosId(UsuariosModel usuariosId, RolesModel rolesId,
			EstadosModel estadosId);

	Optional<UsuariosRolesModel> findByIdAndEstadosId(Long id, EstadosModel estadosId);
}
