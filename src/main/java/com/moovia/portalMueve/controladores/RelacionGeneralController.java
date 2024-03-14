package com.moovia.portalMueve.controladores;

import java.util.ArrayList;
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

import com.moovia.portalMueve.dto.RelacionGeneralInsertDto;
// import com.moovia.portalMueve.dto.TipoSolicitudDto;
import com.moovia.portalMueve.modelos.EmpresaModel;
import com.moovia.portalMueve.modelos.RelacionGeneralModel;
import com.moovia.portalMueve.modelos.UsuariosModel;
import com.moovia.portalMueve.modelos.UsuariosRolesModel;
import com.moovia.portalMueve.servicios.EmpresaService;
import com.moovia.portalMueve.servicios.EstadosService;
import com.moovia.portalMueve.servicios.RelacionGeneralService;
import com.moovia.portalMueve.servicios.RolesService;
// import com.moovia.portalMueve.servicios.TipoSolicitudService;
import com.moovia.portalMueve.servicios.UsuarioService;
import com.moovia.portalMueve.servicios.UsuariosRolesService;
// import com.moovia.portalMueve.servicios.UsuariosService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class RelacionGeneralController {
	@Autowired
	private RelacionGeneralService relacionGeneralService;

	@Autowired
	private UsuariosRolesService usuariosRolesService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private RolesService rolesService;

	@Autowired
	private EstadosService estadosService;

	@SuppressWarnings("serial")
	@GetMapping("/relacion-general/roles-por-id/{id}")
	public ResponseEntity<?> get_roles_por_id(@PathVariable("id") Long id) {
		UsuariosRolesModel datos = this.usuariosRolesService.buscarPorIdActivo(id);
		if (datos == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{
					put("mensaje", "Recurso no disponible ");
				}
			});
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(datos);
		}
	}

	@GetMapping("/relacion-general/roles-por-usuario-y-rol/{id}/{rol_id}")
	public ResponseEntity<?> roles_por_usuario(@PathVariable("id") Long id, @PathVariable("rol_id") Long rol_id) {
		List<RelacionGeneralModel> datos = this.relacionGeneralService.buscarPorUsuariosIdPorEstado(
				this.usuarioService.buscarPorId(id), this.usuariosRolesService.buscarPorId(rol_id));

		return ResponseEntity.status(HttpStatus.OK).body(datos);
	}

	@GetMapping("/relacion-general-por-usuario-roles/{id}")
	public ResponseEntity<?> relacion_general_por_usuario_roles(@PathVariable("id") Long id) {
		List<RelacionGeneralModel> datos = this.relacionGeneralService
				.buscarPorUsuarioRoles(this.usuariosRolesService.buscarPorId(id));
		return ResponseEntity.status(HttpStatus.OK).body(datos);
	}

	@SuppressWarnings("serial")
	@GetMapping("/relacion-general-por-usuario/{id}")
	public ResponseEntity<?> relacion_general_por_usuario(@PathVariable("id") Long id) {
		UsuariosModel usuario = this.usuarioService.buscarPorId(id);
		if (usuario == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{
					put("mensaje", "Recurso no disponible ");
				}
			});
		} else {
			List<UsuariosRolesModel> datos = this.usuariosRolesService.buscarRolesPorUsuario(usuario);
			return ResponseEntity.status(HttpStatus.OK).body(datos);
		}

	}

	/*
	 * { "usuariosRolesId":36, "empresaId": 2, "tipoSolicitudId": 8, "usuariosId":1
	 * }
	 */
	
	/*
	@SuppressWarnings("serial")
	@PostMapping("/relacion-general")
	public ResponseEntity<?> crearRegistro(@RequestBody List<RelacionGeneralInsertDto> dto) {
		List<RelacionGeneralModel> existen = this.relacionGeneralService.buscarPorUsuarioRolesEmpresa(
				this.usuariosRolesService.buscarPorId(dto.get(0).getUsuariosRolesId()),
				this.empresaService.buscarPorId(dto.get(0).getEmpresaId()));
		existen.forEach((existe) -> {
			existe.setEstadosId(this.estadosService.buscarPorId(2L));
			this.relacionGeneralService.guardar(existe);
		});
		dto.forEach((dt) -> {
			RelacionGeneralModel relacion = this.relacionGeneralService.existeRelacionGeneral(
					this.usuariosRolesService.buscarPorId(dt.getUsuariosRolesId()),
					this.empresaService.buscarPorId(dt.getEmpresaId()),
					this.tipoSolicitudService.buscarPorId(dt.getTipoSolicitudId()),
					this.usuarioService.buscarPorId(dt.getUsuariosId()));

			if (relacion == null) {
				this.relacionGeneralService.guardar(new RelacionGeneralModel(
						this.usuariosRolesService.buscarPorId(dt.getUsuariosRolesId()),
						this.empresaService.buscarPorId(dt.getEmpresaId()),
						this.tipoSolicitudService.buscarPorId(dt.getTipoSolicitudId()),
						this.estadosService.buscarPorId(1L), this.usuarioService.buscarPorId(dt.getUsuariosId())));
			} else {
				relacion.setEstadosId(this.estadosService.buscarPorId(1L));
				this.relacionGeneralService.guardar(relacion);
			}

		});

		return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
			{
				put("mensaje", "Se creó el registro exitosamente");
			}
		});
	}

	@SuppressWarnings("serial")
	@GetMapping("/roles-empresa-usuario/{usuario_id}/{rol_id}")
	public ResponseEntity<?> metodo_get_roles_por_empresa_usuario(@PathVariable("usuario_id") Long usuario_id,
			@PathVariable("rol_id") Long rol_id) {
		UsuariosModel usuario = this.usuarioService.buscarPorId(usuario_id);
		UsuariosRolesModel rol = this.usuariosRolesService.buscarPorId(rol_id);
		if (usuario == null || rol == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{
					put("mensaje", "Recurso no disponible");
				}
			});
		}
		List<RelacionGeneralModel> relacion = this.relacionGeneralService.buscarPorUsuariosIdPorEstado(usuario, rol);
		return ResponseEntity.status(HttpStatus.OK).body(relacion);
	}

	@SuppressWarnings("serial")
	@GetMapping("/roles-empresa-usuario-rol/{usuario_id}/{rol_id}/{empresa_id}")
	public ResponseEntity<?> metodo_get_roles_por_empresa__rol_usuario(@PathVariable("usuario_id") Long usuario_id,
			@PathVariable("rol_id") Long rol_id, @PathVariable("empresa_id") Long empresa_id) {
		UsuariosModel usuario = this.usuarioService.buscarPorId(usuario_id);
		UsuariosRolesModel rol = this.usuariosRolesService.buscarPorId(rol_id);
		EmpresaModel empresa = this.empresaService.buscarPorId(empresa_id);
		if (usuario == null || rol == null || empresa == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{
					put("mensaje", "Recurso no disponible");
				}
			});
		}
		List<RelacionGeneralModel> relacion = this.relacionGeneralService.buscarPorUsuariosIdEmpresaIdPorEstado(usuario,
				rol, empresa);
		List<TipoSolicitudDto> tipoSolicitudDto = new ArrayList<>();
		relacion.forEach((rela) -> {
			tipoSolicitudDto.add(new TipoSolicitudDto(rela.getTipoSolicitudId().getId(),
					rela.getTipoSolicitudId().getNombre(), rela.getTipoSolicitudId().getDescripcion()));
		});
		return ResponseEntity.status(HttpStatus.OK).body(tipoSolicitudDto);
	}

	@SuppressWarnings("serial")
	@GetMapping("/roles-empresa-usuario-rol-limpio/{usuario_id}/{rol_id}/{empresa_id}")
	public ResponseEntity<?> metodo_get_roles_por_empresa__rol_usuario_limpio(
			@PathVariable("usuario_id") Long usuario_id, @PathVariable("rol_id") Long rol_id,
			@PathVariable("empresa_id") Long empresa_id) {
		UsuariosModel usuario = this.usuarioService.buscarPorId(usuario_id);
		UsuariosRolesModel rol = this.usuariosRolesService.buscarPorId(rol_id);
		EmpresaModel empresa = this.empresaService.buscarPorId(empresa_id);
		if (usuario == null || rol == null || empresa == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{
					put("mensaje", "Recurso no disponible");
				}
			});
		}
		List<RelacionGeneralModel> relacion = this.relacionGeneralService.buscarPorUsuariosIdEmpresaIdPorEstado(usuario,
				rol, empresa);
		List<TipoSolicitudDto> tipoSolicitudDto = new ArrayList<>();
		relacion.forEach((rela) -> {
			tipoSolicitudDto.add(new TipoSolicitudDto(rela.getTipoSolicitudId().getId(),
					rela.getTipoSolicitudId().getNombre(), rela.getTipoSolicitudId().getDescripcion()));
		});
		return ResponseEntity.status(HttpStatus.OK).body(relacion);
	}

	@SuppressWarnings("serial")
	public ResponseEntity<?> crearRegistro3333(@RequestBody RelacionGeneralInsertDto dto) {
		RelacionGeneralModel relacion = this.relacionGeneralService.existeRelacionGeneral(
				this.usuariosRolesService.buscarPorId(dto.getUsuariosRolesId()),
				this.empresaService.buscarPorId(dto.getEmpresaId()),
				this.tipoSolicitudService.buscarPorId(dto.getTipoSolicitudId()),
				this.usuarioService.buscarPorId(dto.getUsuariosId()));
		if (relacion == null) {
			this.relacionGeneralService.guardar(new RelacionGeneralModel(
					this.usuariosRolesService.buscarPorId(dto.getUsuariosRolesId()),
					this.empresaService.buscarPorId(dto.getEmpresaId()),
					this.tipoSolicitudService.buscarPorId(dto.getTipoSolicitudId()),
					this.estadosService.buscarPorId(1L), this.usuarioService.buscarPorId(dto.getUsuariosId())));
			return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
				{
					put("mensaje", "Se creó el registro exitosamente");
				}
			});
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{
					put("mensaje", "No es posible crear el registro porque ya existe ");
				}
			});
		}
	}

	@SuppressWarnings("serial")
	@DeleteMapping("/relacion-general/{id}")
	public ResponseEntity<?> relacion_general_eliminar(@PathVariable("id") Long id) {
		RelacionGeneralModel relacion = this.relacionGeneralService.buscarPorId(id);
		if (relacion == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {
				{
					put("mensaje", "Recurso no disponible");
				}
			});
		} else {
			relacion.setEstadosId(this.estadosService.buscarPorId(2L));
			this.relacionGeneralService.guardar(relacion);
			return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
				{
					put("mensaje", "Se eliminó el registro exitosamente");
				}
			});
		}
	}*/
}