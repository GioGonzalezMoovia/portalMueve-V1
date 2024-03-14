package com.moovia.portalMueve.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.moovia.portalMueve.modelos.EmpresaModel;
import com.moovia.portalMueve.repositorios.IEmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private IEmpresaRepository repositorio;
	public List<EmpresaModel> listar()
	{
		return this.repositorio.findAll(Sort.by("id").descending());
	}
	public void guardar(EmpresaModel modelo) 
	{
		this.repositorio.save(modelo);
	}
	public EmpresaModel buscarPorId(Long id) {
		Optional<EmpresaModel> optional = this.repositorio.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	} 
	public void eliminar(Long id) {
		this.repositorio.deleteById(id);
		
	}
	
}
