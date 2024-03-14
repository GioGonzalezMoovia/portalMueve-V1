package com.moovia.portalMueve.controladores;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moovia.portalMueve.dto.RolesModulosDto;
import com.moovia.portalMueve.modelos.RolesModulosModel;
import com.moovia.portalMueve.servicios.ModulosService;
import com.moovia.portalMueve.servicios.RolesModulosService;
import com.moovia.portalMueve.servicios.RolesService;

// @CrossOrigin(origins = "http://127.0.0.1:5173", maxAge = 3600)
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class PermisosController {
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private ModulosService modulosService;
	
	@Autowired
	private RolesModulosService rolesModulosService;
	
	@GetMapping("/permisos-modulos/{rol_id}/{modulo_id}")
	public ResponseEntity<?> getDatosPorId(@PathVariable("rol_id") Long rol_id, @PathVariable("modulo_id") Long modulo_id)
	{
		RolesModulosModel existe =this.rolesModulosService.buscarRolModulo(this.rolesService.buscarPorId(rol_id), this.modulosService.buscarPorId(modulo_id));
		if(existe!=null) 
		{
			return ResponseEntity.status(HttpStatus.OK).body(existe);
		}else 
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;

				{
					put("mensaje", "Registro no disponible");
				}
			});
		}
	}
	
	@SuppressWarnings("serial")
	@PostMapping("/permisos-modulos")
	public ResponseEntity<?> metodo_post(@RequestBody List<RolesModulosDto> dto)
	{
		Long rol=dto.get(0).getRol_id();
		//System.out.println("El rol es "+rol+"\n");
		List<RolesModulosModel> modulosPorRol = this.rolesModulosService.modulosPorRol(this.rolesService.buscarPorId(rol));
		
		modulosPorRol.forEach((r)->{
			this.rolesModulosService.eliminar(r.getId());
			 //System.out.println(r.getId());
		});
		dto.forEach((d)->{
 
			System.out.println("rol="+d.getRol_id()+" | "+this.rolesService.buscarPorId(d.getRol_id()));
			System.out.println("modulo="+d.getModulo_id()+" | "+this.modulosService.buscarPorId(d.getModulo_id()));
		 
			System.out.println("----------------------------------");
			this.rolesModulosService.guardar
			(
					new RolesModulosModel
					(
						this.rolesService.buscarPorId(d.getRol_id()), 		
						this.modulosService.buscarPorId(d.getModulo_id())
					)
			);
		});
		return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
			{
				put("mensaje", "Se creó el registro exitosamente");
			}
		});
	}
	@SuppressWarnings("serial")
	@DeleteMapping("/permisos-modulos/{id}")
	public ResponseEntity<?> getDatosDelete(@PathVariable("id") Long id)
	{
		RolesModulosModel existe=this.rolesModulosService.buscarPorId(id);
		if(existe!=null) 
		{
			this.rolesModulosService.eliminar(id);
			return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
				{
					put("mensaje", "Se eliminó el registro exitosamente");
				}
			});
		}else 
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{
					put("mensaje", "Registro no disponible");
				}
			});
		}
	}
	
}
