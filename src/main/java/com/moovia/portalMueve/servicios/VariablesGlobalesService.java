package com.moovia.portalMueve.servicios;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moovia.portalMueve.repositorios.VariablesGlobalesRepository;
import com.moovia.portalMueve.modelos.VariablesGlobalesModel;

@Service
public class VariablesGlobalesService {
	@Autowired
	private VariablesGlobalesRepository repositorio;
	
	public VariablesGlobalesModel buscarPorId(Long id) {
		Optional<VariablesGlobalesModel> optional = repositorio.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
}
