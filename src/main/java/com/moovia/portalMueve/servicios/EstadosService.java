package com.moovia.portalMueve.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.moovia.portalMueve.modelos.EstadosModel;
import com.moovia.portalMueve.repositorios.IEstadosRepository;


@Service
public class EstadosService {
	@Autowired
	private IEstadosRepository repositorio;
	public List<EstadosModel> listar()
	{
		return this.repositorio.findAll(Sort.by("id").descending());
	}
	public EstadosModel buscarPorId(Long id) {
		Optional<EstadosModel> optional = this.repositorio.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	} 
}
