package com.moovia.portalMueve.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.moovia.portalMueve.modelos.ModulosModel;
import com.moovia.portalMueve.repositorios.IModulosRepository;

@Service
@Primary
public class ModulosService {

	@Autowired
	private IModulosRepository repositorio;

	public List<ModulosModel> listar() {
		return this.repositorio.findAll(Sort.by("id").descending());
	}

	public void guardar(ModulosModel modelo) {
		this.repositorio.save(modelo);
	}

	public ModulosModel buscarPorId(Long id) {
		Optional<ModulosModel> optional = this.repositorio.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public void eliminar(Long id) {
		this.repositorio.deleteById(id);

	}

}
