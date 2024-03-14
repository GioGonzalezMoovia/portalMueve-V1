package com.moovia.portalMueve.controladores;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moovia.portalMueve.dto.JwtResponseDto;
import com.moovia.portalMueve.dto.LoginDto;
import com.moovia.portalMueve.jwt.JwtService;
import com.moovia.portalMueve.modelos.UsuariosModel;
import com.moovia.portalMueve.servicios.UsuarioService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private JwtService jwtService;

	@SuppressWarnings("serial")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDto dto) {
		UsuariosModel usuario = this.usuarioService.buscarPorCorreoActivo(dto.getCorreo());
		if (usuario == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
				{
					put("Mensaje", "Las credenciales ingresadas no son válidas");
				}
			});
		} else {
			if (this.passwordEncode.matches(dto.getPassword(), usuario.getPassword())) {
				String token = this.jwtService.generateToken(usuario.getCorreo());
				return ResponseEntity.status(HttpStatus.OK)
						.body(new JwtResponseDto(usuario.getId(), usuario.getNombre() + " " + usuario.getApellido(),
								usuario.getRolesId().getNombre(), usuario.getRolesId().getId(), token));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
					{
						put("Mensaje", "Las credenciales ingresadas no son válidas");
					}
				});
			}
		}
	}
}