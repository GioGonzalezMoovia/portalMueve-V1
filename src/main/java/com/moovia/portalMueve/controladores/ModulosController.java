package com.moovia.portalMueve.controladores;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moovia.portalMueve.dto.ModulosDto;
import com.moovia.portalMueve.modelos.ModulosModel;
import com.moovia.portalMueve.servicios.ModulosService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class ModulosController {
	
	@Autowired
	private ModulosService modulosService;
	
	
	@GetMapping("/modulos")
	public ResponseEntity<?> metodo_get()
	{
		System.out.println("AQUI ES");
		return ResponseEntity.status(HttpStatus.OK).body(this.modulosService.listar());
	}
	
	@GetMapping("/modulos/{id}")
	public ResponseEntity<?> metodo_get_con_parametros(@PathVariable("id") Long id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.modulosService.buscarPorId(id));
	}
	

	@SuppressWarnings("serial")
	@PostMapping("/modulos") 
	public ResponseEntity<?> metodo_post(@RequestBody ModulosDto dto)
	{
		this.modulosService.guardar(new ModulosModel(dto.getNombre()));
		return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
			{
				put("Mensaje", "Se creó el registro exitosamente " );
			}
		});
		
	}
	@SuppressWarnings("serial")
	@PutMapping("/modulos/{id}")
	public ResponseEntity<?> getModulosPut(@PathVariable("id") Long id,@RequestBody ModulosDto dto)
	{
		ModulosModel modulo= this.modulosService.buscarPorId(id);
		if(modulo!=null) 
		{
			modulo.setNombre(dto.getNombre());
			this.modulosService.guardar(modulo);
			return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
				{
					put("Mensaje","Se modificó el registro exitosamente"   );
				}
			});
			
		}else 
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{
					put("Mensaje","Recurso no disponible ");
				}
			});
		}
		
	}
	@SuppressWarnings("serial")
	@DeleteMapping("/modulos/{id}")
	public ResponseEntity<?> getModulosDelete(@PathVariable("id") Long id)
	{
		ModulosModel modulo= this.modulosService.buscarPorId(id);
		if(modulo!=null) 
		{
			this.modulosService.eliminar(id);
			return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
				{
					put("Mensaje","Se eliminó el registro exitosamente"   );
				}
			});
			
		}else 
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{
					put("Mensaje","Recurso no disponible ");
				}
			});
		}
	}
}
