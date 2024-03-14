package com.moovia.portalMueve.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.moovia.portalMueve.modelos.SucursalModel;
import com.moovia.portalMueve.repositorios.ISucursalRepository;

@Service
public class SucursalService {
	
	@Autowired
	private ISucursalRepository repositorio;

	public List<SucursalModel> listar() {
		return this.repositorio.findAll(Sort.by("id").descending());
	}

	public void guardar(SucursalModel modelo) {
		this.repositorio.save(modelo);
	}

	public SucursalModel buscarPorId(Long id) {
		Optional<SucursalModel> optional = this.repositorio.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public void eliminar(Long id) {
		this.repositorio.deleteById(id);

	}
}
