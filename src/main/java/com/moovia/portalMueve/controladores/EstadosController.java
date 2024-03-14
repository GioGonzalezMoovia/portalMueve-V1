package com.moovia.portalMueve.controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moovia.portalMueve.servicios.EstadosService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class EstadosController {
	@Autowired
	private EstadosService estadosService;
	
	
	@GetMapping("/estados")
	public ResponseEntity<?> metodo_get()
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.estadosService.listar());
	}
}
