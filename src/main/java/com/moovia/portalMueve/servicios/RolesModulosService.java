package com.moovia.portalMueve.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moovia.portalMueve.modelos.ModulosModel;
import com.moovia.portalMueve.modelos.RolesModel;
import com.moovia.portalMueve.modelos.RolesModulosModel;
import com.moovia.portalMueve.repositorios.IRolesModulosRepository;

@Service
public class RolesModulosService {
	@Autowired
	private IRolesModulosRepository repositorio;
	
	public void guardar(RolesModulosModel modelo) 
	{
		this.repositorio.save(modelo);
	}
	public RolesModulosModel buscarPorId(Long id) {
		Optional<RolesModulosModel> optional = repositorio.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	public RolesModulosModel buscarRolModulo(RolesModel rolesId, ModulosModel modulosId) 
	{
		return this.repositorio.findByRolesIdAndModulosId(rolesId, modulosId);
	}
	public List<RolesModulosModel> modulosPorRol(RolesModel rolesId)
	{
		return this.repositorio.findByRolesId(rolesId);
	}
	public void eliminar(Long id) {
		this.repositorio.deleteById(id);
		
	}
}
