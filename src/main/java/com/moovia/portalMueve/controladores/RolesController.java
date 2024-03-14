package com.moovia.portalMueve.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moovia.portalMueve.servicios.RolesService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class RolesController {
	@Autowired
	private RolesService rolesService;
	
	@GetMapping("/roles")
	public ResponseEntity<?> metodo_get()
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.rolesService.listar());
	}
	
	@GetMapping("/listarRoles")
	public ResponseEntity<?> metodo_get2()
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.rolesService.listar());
	}
}
