package com.moovia.portalMueve.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.moovia.portalMueve.modelos.EmpresaModel;
import com.moovia.portalMueve.modelos.ModulosModel;
import com.moovia.portalMueve.modelos.RelacionGeneralModel;
import com.moovia.portalMueve.modelos.RolesModel;
import com.moovia.portalMueve.modelos.SucursalModel;
import com.moovia.portalMueve.modelos.UsuariosModel;
import com.moovia.portalMueve.modelos.UsuariosRolesModel;
import com.moovia.portalMueve.repositorios.IRelacionGeneralRepository;
// import com.moovia.portalMueve.modelos.TipoSolicitudModel;

@Service
public class RelacionGeneralService {
	@Autowired
	private IRelacionGeneralRepository repositorio;

	@Autowired
	private EstadosService estadosService;

	public List<RelacionGeneralModel> listar() {
		return this.repositorio.findAll(Sort.by("id").descending());
	}

	public void guardar(RelacionGeneralModel modelo) {
		this.repositorio.save(modelo);
	}

	public RelacionGeneralModel guardarRetorno(RelacionGeneralModel modelo) {
		return this.repositorio.save(modelo);
	}

	public RelacionGeneralModel buscarPorId(Long id) {
		Optional<RelacionGeneralModel> optional = this.repositorio.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public List<RelacionGeneralModel> buscarPorUsuarioRoles(UsuariosRolesModel usuariosRolesId) {
		return this.repositorio.findByUsuariosRolesId(usuariosRolesId);
	}

	public List<RelacionGeneralModel> buscarPorUsuarios(UsuariosModel usuariosId) {
		return this.repositorio.findByUsuariosIdAndEstadosId(usuariosId, this.estadosService.buscarPorId(1L));
	}

	public List<RelacionGeneralModel> buscarPorUsuarioRolesEmpresa(UsuariosRolesModel usuariosRolesId,
			EmpresaModel empresaId) {
		return this.repositorio.findByUsuariosRolesIdAndEmpresaId(usuariosRolesId, empresaId,
				Sort.by("empresaId").ascending());
	}

	public List<RelacionGeneralModel> buscarPorUsuarioRolEmpresaActivo(UsuariosRolesModel usuariosRolesId,
			EmpresaModel empresaId, UsuariosModel usuariosId) {
		return this.repositorio.findByUsuariosRolesIdAndEmpresaIdAndUsuariosIdAndEstadosId(usuariosRolesId, empresaId,
				usuariosId, this.estadosService.buscarPorId(1L));
	}

	public List<RelacionGeneralModel> buscarPorUsuariosIdPorEstado(UsuariosModel usuariosId,
			UsuariosRolesModel usuariosRolesId) {
		return this.repositorio.findByUsuariosIdAndUsuariosRolesIdAndEstadosId(usuariosId, usuariosRolesId,
				this.estadosService.buscarPorId(1L));
	}

	public List<RelacionGeneralModel> buscarPorUsuariosIdEmpresaIdPorEstado(UsuariosModel usuariosId,
			UsuariosRolesModel usuariosRolesId, EmpresaModel empresaId) {
		return this.repositorio.findByUsuariosIdAndUsuariosRolesIdAndEmpresaIdAndEstadosId(usuariosId, usuariosRolesId,
				empresaId, this.estadosService.buscarPorId(1L));
	}
	
	/*public RelacionGeneralModel existeRelacionGeneralPorEstado(UsuariosRolesModel usuariosRolesId,
			EmpresaModel empresaId, RolesModel rolesId, UsuariosModel usuariosId) {
		return this.repositorio.findByUsuariosRolesIdAndEmpresaIdAndTipoSolicitudIdAndUsuariosIdAndEstadosId(
				usuariosRolesId, empresaId, rolesId, usuariosId, this.estadosService.buscarPorId(1L));
	}

	public RelacionGeneralModel existeRelacionGeneral(UsuariosRolesModel usuariosRolesId, EmpresaModel empresaId,
			TipoSolicitudModel tipoSolicitudId, UsuariosModel usuariosId) {
		return this.repositorio.findByUsuariosRolesIdAndEmpresaIdAndTipoSolicitudIdAndUsuariosIdAndEstadosId(
				usuariosRolesId, empresaId, tipoSolicitudId, usuariosId, this.estadosService.buscarPorId(2L));
	}

	public RelacionGeneralModel getRelacionGeneral(UsuariosRolesModel usuariosRolesId, EmpresaModel empresaId,
			TipoSolicitudModel tipoSolicitudId, UsuariosModel usuariosId) {
		return this.repositorio.findByUsuariosRolesIdAndEmpresaIdAndTipoSolicitudIdAndUsuariosIdAndEstadosId(
				usuariosRolesId, empresaId, tipoSolicitudId, usuariosId, this.estadosService.buscarPorId(1L));
	}*/

	public void eliminarPorRol(UsuariosRolesModel usuariosRolesId) {
		this.repositorio.deleteByUsuariosRolesId(usuariosRolesId);
	}

	public void eliminar(Long id) {
		this.repositorio.deleteById(id);

	}

}