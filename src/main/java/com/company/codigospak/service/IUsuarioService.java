package com.company.codigospak.service;

import org.springframework.http.ResponseEntity;

import com.company.codigospak.response.UsuarioResponseRest;
import com.company.codigospak.perfiles.Usuarios;

public interface IUsuarioService {
	
	public ResponseEntity<UsuarioResponseRest> buscarUsuario();
	
	public ResponseEntity<UsuarioResponseRest> buscarPorId(Long id);
	
	public ResponseEntity<UsuarioResponseRest> crearUsuario(Usuarios usuarios); 
	
	public ResponseEntity<UsuarioResponseRest> editarUsuario(Usuarios usuarios, Long id);
	
	public ResponseEntity<UsuarioResponseRest> eliminarUsuario(Long id);

}
