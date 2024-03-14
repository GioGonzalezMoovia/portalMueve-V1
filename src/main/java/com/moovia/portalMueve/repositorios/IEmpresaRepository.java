package com.moovia.portalMueve.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moovia.portalMueve.modelos.EmpresaModel;

public interface IEmpresaRepository extends JpaRepository<EmpresaModel, Long> {

}
