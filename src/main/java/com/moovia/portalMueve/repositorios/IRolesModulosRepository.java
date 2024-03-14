package com.moovia.portalMueve.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moovia.portalMueve.modelos.ModulosModel;
import com.moovia.portalMueve.modelos.RolesModel;
import com.moovia.portalMueve.modelos.RolesModulosModel;

public interface IRolesModulosRepository extends JpaRepository<RolesModulosModel, Long> {
	RolesModulosModel findByRolesIdAndModulosId(RolesModel rolesId, ModulosModel modulosId);
	List<RolesModulosModel> findByRolesId(RolesModel rolesId);
}
