package com.moovia.portalMueve.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.moovia.portalMueve.modelos.EstadosModel;
import com.moovia.portalMueve.modelos.RolesModel;
import com.moovia.portalMueve.modelos.UsuariosModel;
import com.moovia.portalMueve.modelos.UsuariosRolesModel;
import com.moovia.portalMueve.repositorios.IUsuariosRolesRepository;

@Service
public class UsuariosRolesService {
	@Autowired
	private IUsuariosRolesRepository repositorio;
	
	@Autowired
	private EstadosService estadosService;
	
	public List<UsuariosRolesModel> listar()
	{
		return this.repositorio.findAll(Sort.by("id").descending());
	}
	public void guardar(UsuariosRolesModel modelo) 
	{
		this.repositorio.save(modelo);
	}
	public UsuariosRolesModel buscarPorId(Long id) {
		Optional<UsuariosRolesModel> optional = this.repositorio.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	} 
	public UsuariosRolesModel buscarPorIdActivo(Long id) {
		Optional<UsuariosRolesModel> optional = this.repositorio.findByIdAndEstadosId(id, this.estadosService.buscarPorId(1L));
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	} 
	public void eliminarPorUsuario(UsuariosModel usuariosId) {
		this.repositorio.deleteByUsuariosId(usuariosId);
		
	}
	public List<UsuariosRolesModel> buscarRolesPorUsuario(UsuariosModel usuariosId)
	{
		return this.repositorio.findByUsuariosIdAndEstadosId(usuariosId, this.estadosService.buscarPorId(1L));
	}
	public UsuariosRolesModel buscarUsuarioRoles(UsuariosModel usuariosId, RolesModel rolesId) 
	{
		return this.repositorio.findByUsuariosIdAndRolesIdAndEstadosId(usuariosId, rolesId, this.estadosService.buscarPorId(2L));
	}
	public void eliminar(Long id) {
		this.repositorio.deleteById(id);
		
	}
}
