package com.moovia.portalMueve.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moovia.portalMueve.modelos.ModulosModel;

public interface IModulosRepository extends JpaRepository<ModulosModel, Long> {
	 
}
