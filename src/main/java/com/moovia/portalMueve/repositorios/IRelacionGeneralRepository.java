package com.moovia.portalMueve.repositorios;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.moovia.portalMueve.modelos.EmpresaModel;
import com.moovia.portalMueve.modelos.EstadosModel;
import com.moovia.portalMueve.modelos.RelacionGeneralModel;
import com.moovia.portalMueve.modelos.UsuariosModel;
import com.moovia.portalMueve.modelos.UsuariosRolesModel;

public interface IRelacionGeneralRepository extends JpaRepository<RelacionGeneralModel, Long> {
	List<RelacionGeneralModel> findByUsuariosRolesId(UsuariosRolesModel usuariosRolesId);

	List<RelacionGeneralModel> findByUsuariosRolesIdAndEmpresaId(UsuariosRolesModel usuariosRolesId,
			EmpresaModel empresaId, Sort sort);

	List<RelacionGeneralModel> findByUsuariosRolesIdAndEstadosId(UsuariosRolesModel usuariosRolesId,
			EstadosModel estadosId);

	List<RelacionGeneralModel> findByUsuariosIdAndEstadosId(UsuariosModel usuariosId, EstadosModel estadosId);

	void deleteByUsuariosRolesId(UsuariosRolesModel usuariosRolesId);

	List<RelacionGeneralModel> findByUsuariosIdAndUsuariosRolesIdAndEstadosId(UsuariosModel usuariosId,
			UsuariosRolesModel usuariosRolesId, EstadosModel estadosId);

	List<RelacionGeneralModel> findByUsuariosIdAndUsuariosRolesIdAndEmpresaIdAndEstadosId(UsuariosModel usuariosId,
			UsuariosRolesModel usuariosRolesId, EmpresaModel empresaId, EstadosModel estadosId);

	List<RelacionGeneralModel> findByUsuariosRolesIdAndEmpresaIdAndUsuariosIdAndEstadosId(
			UsuariosRolesModel usuariosRolesId, EmpresaModel empresaId, UsuariosModel usuariosId,
			EstadosModel estadosId);

}