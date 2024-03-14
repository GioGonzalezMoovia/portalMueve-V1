package com.moovia.portalMueve.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moovia.portalMueve.dto.JwtResponseDto;
import com.moovia.portalMueve.dto.UsuarioDto;
import com.moovia.portalMueve.dto.UsuarioUpdate2Dto;
import com.moovia.portalMueve.dto.UsuarioUpdateDto;
import com.moovia.portalMueve.dto.UsuariosResponseDto;
import com.moovia.portalMueve.jwt.JwtService;
import com.moovia.portalMueve.modelos.UsuariosModel;
import com.moovia.portalMueve.servicios.EstadosService;
import com.moovia.portalMueve.servicios.ModulosService;
import com.moovia.portalMueve.servicios.RolesService;
import com.moovia.portalMueve.servicios.SucursalService;
import com.moovia.portalMueve.servicios.UsuarioService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class UsuariosController {

	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EstadosService estadosService;

	@Autowired
	private RolesService rolesService;

	@Autowired
	private SucursalService sucursalService;
	
	@Autowired
	private ModulosService modulosService;

	@Autowired
	private JwtService jwtService;

	@GetMapping("/listarUsuarios")
	public ResponseEntity<?> metodo_get2() {
		return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.listar2());
	}

	@GetMapping("/usuarios")
	public ResponseEntity<?> metodo_get() {
		List<UsuariosModel> datos = this.usuarioService.listar();
		List<UsuariosResponseDto> usuarios = new ArrayList<>();
		datos.forEach((dato) -> {
			usuarios.add(new UsuariosResponseDto(dato.getId(), dato.getNombre(), dato.getApellido(), dato.getCorreo(), dato.getCodigo(),
					dato.getRolesId(), dato.getRolesId().getNombre(), dato.getEstadosId(),
					dato.getSucursalId()));
		});
		return ResponseEntity.status(HttpStatus.OK).body(usuarios);
	}

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> metodo_get_con_parametros(@PathVariable("id") Long id) {
		UsuariosModel dato = this.usuarioService.buscarPorId(id);
		/*
		 * UsuariosResponseDto usuario=new UsuariosResponseDto(dato.getId(),
		 * dato.getNombre(), dato.getCorreo(), dato.getRolesId(),
		 * dato.getRolesId().getNombre(), dato.getEstadosId().getId());
		 */

		return ResponseEntity.status(HttpStatus.OK)
				.body(new JwtResponseDto(dato.getId(), dato.getNombre() + " " + dato.getApellido(),
						dato.getRolesId().getNombre(), dato.getRolesId().getId(),
						this.jwtService.generateToken(dato.getCorreo())));
	}
	
	@GetMapping("/usuarios-por-id/{id}")
	public ResponseEntity<?> metodo_get_por_usuario_id(@PathVariable("id") Long id) {
		UsuariosModel usuario = this.usuarioService.buscarPorUsuarioId(id);
		if (usuario == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{
					put("mensaje", "Recurso no disponible ");
				}
			});
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(usuario);
		}
	}

	/*
	 * { "nombre":"César", "apellido":"Cancino Zapata", "correo":"info2@tamila.cl",
	 * "password":"123456" }
	 */
	@SuppressWarnings("serial")
	@PostMapping("/usuarios")
	public ResponseEntity<?> metodo_post(@RequestBody UsuarioDto dto) {
		UsuariosModel usuario = this.usuarioService.buscarPorCorreo(dto.getCorreo());
		if (usuario != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{
					put("Mensaje", "El E-Mail ingresado no está disponible");
				}
			});
		} else {
			String token = UUID.randomUUID().toString();
			this.usuarioService.guardar(new UsuariosModel(dto.getCorreo(), dto.getNombre(), dto.getApellido(), 
					this.passwordEncode.encode(dto.getPassword()), token, new Date(), dto.getCodigoUsuario(),
					this.estadosService.buscarPorId(2L), this.rolesService.buscarPorId(2L),
					this.sucursalService.buscarPorId(2L)));
			return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
				{
					put("Mensaje", "se creó el usuario exitosamente");
				}
			});
		}
	}

	@SuppressWarnings("serial")
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<?> metodo_put(@PathVariable("id") Long id, @RequestBody UsuarioUpdateDto dto) {
		UsuariosModel usuario = this.usuarioService.buscarPorId(id);
		usuario.setNombre(dto.getNombre());
		usuario.setApellido(dto.getApellido());
		usuario.setCorreo(dto.getCorreo());
		usuario.setPassword(dto.getPassword());
		usuario.setRolesId(this.rolesService.buscarPorId(dto.getRol_id()));
		this.usuarioService.guardar(usuario);
		return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
			{
				put("Mensaje", "se modificó el usuario exitosamente");
			}
		});
	}

	/*
	 * { "nombre":"Gio", "apellido":"González", "correo":"gio@gmail.com",
	 * "password":"123456", "rol_id":1 }
	 */
	@SuppressWarnings("serial")
	@PutMapping("/usuarios-estado/{id}")
	public ResponseEntity<?> metodo_put_estado(@PathVariable("id") Long id, @RequestBody UsuarioUpdate2Dto dto) {
		UsuariosModel usuario = this.usuarioService.buscarPorId(id);

		usuario.setEstadosId(this.estadosService.buscarPorId(dto.getEstado_id()));
		this.usuarioService.guardar(usuario);
		return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
			{
				put("Mensaje", "se modificó el usuario exitosamente");
			}
		});
	}

	/*
	 * { "estado_id":1 }
	 */
	@SuppressWarnings({ "serial" })
	@GetMapping("/verificacion/{token}")
	public ResponseEntity<?> verificacion(@PathVariable("token") String token) {
		if (token == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {
				{
					put("mensaje", "Recurso no disponible");
				}
			});
		}
		UsuariosModel usuario = this.usuarioService.buscarPorToken(token, this.estadosService.buscarPorId(2L));
		if (usuario == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>() {
				{
					put("mensaje", "Recurso no disponible");
				}
			});
		}
		usuario.setToken("");
		usuario.setEstadosId(this.estadosService.buscarPorId(1L));
		;
		this.usuarioService.guardar(usuario);
		return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
			{
				put("Mensaje", "Se actualizó el registro exitosamente");
			}
		});
	}
}