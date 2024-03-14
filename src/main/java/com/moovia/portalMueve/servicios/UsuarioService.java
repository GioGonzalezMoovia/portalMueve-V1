package com.moovia.portalMueve.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.moovia.portalMueve.modelos.EstadosModel;
import com.moovia.portalMueve.modelos.UsuariosModel;
import com.moovia.portalMueve.repositorios.IUsuariosRepository;

@Service
public class UsuarioService {
	@Autowired
	private IUsuariosRepository repositorio;

	@Autowired
	private EstadosService estadosService;
	
	public List<UsuariosModel> listar2() {
		return this.repositorio.findAll(Sort.by("id").ascending());
	}

	public List<UsuariosModel> listar() {
		return this.repositorio.findAll(Sort.by("id").descending());
	}

	public void guardar(UsuariosModel modelo) {
		this.repositorio.save(modelo);
	}

	public UsuariosModel buscarPorId(Long id) {
		Optional<UsuariosModel> optional = this.repositorio.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public UsuariosModel buscarPorCorreo(String correo) {
		return this.repositorio.findByCorreo(correo);
	}

	public UsuariosModel buscarPorCorreoActivo(String correo) {
		Optional<UsuariosModel> optional = this.repositorio.findByCorreoAndEstadosId(correo,
				this.estadosService.buscarPorId(1L));
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;

	}
	
	public UsuariosModel buscarPorUsuarioId(Long UsuarioId) 
	{
		return this.repositorio.findByUsuarioId(UsuarioId);
	}

	public UsuariosModel buscarPorToken(String token, EstadosModel estado) {
		return this.repositorio.findByTokenAndEstadosId(token, estado);
	}

	public void eliminar(Long id) {
		this.repositorio.deleteById(id);

	}
}