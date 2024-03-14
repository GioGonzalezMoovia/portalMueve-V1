package com.moovia.portalMueve.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.moovia.portalMueve.modelos.EstadosModel;
import com.moovia.portalMueve.modelos.UsuariosModel;

@Service
public interface IUsuariosRepository extends JpaRepository<UsuariosModel, Long> {
	UsuariosModel findByCorreo(String correo);

	Optional<UsuariosModel> findByCorreoAndEstadosId(String nombre, EstadosModel estado);

	UsuariosModel findByTokenAndEstadosId(String token, EstadosModel estado);

	UsuariosModel findByUsuarioId(Long UsuarioId);
}
