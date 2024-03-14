package com.moovia.portalMueve.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.moovia.portalMueve.modelos.RolesModel;
import com.moovia.portalMueve.repositorios.IRolesRepository;

@Service
public class RolesService {
	@Autowired
	private IRolesRepository repositorio;
	
	public List<RolesModel> listar()
	{
		return this.repositorio.findAll(Sort.by("id").descending());
	}
	public void guardar(RolesModel modelo) 
	{
		this.repositorio.save(modelo);
	}
	public RolesModel buscarPorId(Long id) {
		Optional<RolesModel> optional = this.repositorio.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	} 
	public void eliminar(Long id) {
		this.repositorio.deleteById(id);
		
	}
}
