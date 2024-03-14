package com.moovia.portalMueve.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="admin_modulos")
@Data
public class ModulosModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String Nombre;
	public ModulosModel(String nombre) {
		
		Nombre = nombre;
	}
	public ModulosModel() {
		
	}
	
	
}