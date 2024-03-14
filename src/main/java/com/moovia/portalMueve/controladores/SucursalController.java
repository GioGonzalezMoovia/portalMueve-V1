package com.moovia.portalMueve.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moovia.portalMueve.dto.SucursalDto;
import com.moovia.portalMueve.dto.UsuariosResponseDto;
import com.moovia.portalMueve.modelos.SucursalModel;
import com.moovia.portalMueve.modelos.UsuariosModel;
import com.moovia.portalMueve.servicios.SucursalService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class SucursalController {

	@Autowired
	private SucursalService sucursalService;

	/*@GetMapping("/sucursal")
	public ResponseEntity<?> metodo_get() {
		return ResponseEntity.status(HttpStatus.OK).body(this.sucursalService.listar());
	}*/
	
	@GetMapping("/sucursal")
	public ResponseEntity<?> metodo_get() {
		List<SucursalModel> datos = this.sucursalService.listar();
		List<SucursalDto> sucursal = new ArrayList<>();
		datos.forEach((dato) -> {
			sucursal.add(new SucursalDto(dato.getId(), dato.getNombre()));
		});
		return ResponseEntity.status(HttpStatus.OK).body(sucursal);
	}
	
	@GetMapping("/sucursal/{id}")
	public ResponseEntity<?> metodo_get_con_parametros(@PathVariable("id") Long id) {
		SucursalModel dato = this.sucursalService.buscarPorId(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new SucursalDto(dato.getId(), dato.getNombre()));
	}

}