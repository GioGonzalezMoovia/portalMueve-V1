package com.moovia.portalMueve.jwt;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException;  
import org.springframework.stereotype.Service;

import com.moovia.portalMueve.modelos.UsuariosModel;
import com.moovia.portalMueve.repositorios.IUsuariosRepository;
import com.moovia.portalMueve.servicios.EstadosService;

import java.util.Optional; 

@Service
public class UserInfoService implements UserDetailsService  {
	
	@Autowired
	private IUsuariosRepository repository;
	
	@Autowired
	private EstadosService estadoservice;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
		
		Optional<UsuariosModel> userDetail = repository.findByCorreoAndEstadosId(username, this.estadoservice.buscarPorId(1L)); 

		// Converting userDetail to UserDetails 
		return userDetail.map(UserInfoDetails::new) 
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + username)); 
	} 
}
