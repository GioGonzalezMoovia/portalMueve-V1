package com.moovia.portalMueve.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moovia.portalMueve.modelos.EstadosModel;

public interface IEstadosRepository extends JpaRepository<EstadosModel, Long> {

}
