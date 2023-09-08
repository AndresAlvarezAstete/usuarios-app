package com.company.codigospak.controllers.perfiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.codigospak.response.UsuarioResponseRest;
import com.company.codigospak.service.IUsuarioService;
import com.company.codigospak.perfiles.Usuarios;

@RestController
@RequestMapping("v1")
public class UsuarioRestController {

	@Autowired
	private IUsuarioService service;
	
	@GetMapping("/usuarios")
	public ResponseEntity<UsuarioResponseRest> consultaUsuario() {
		
		ResponseEntity<UsuarioResponseRest> response = service.buscarUsuario();
		return response;
	}
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<UsuarioResponseRest> buscarPorId(@PathVariable Long id) {
		
		ResponseEntity<UsuarioResponseRest> response = service.buscarPorId(id);
		return response;
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<UsuarioResponseRest> crearUsuario(@RequestBody Usuarios request) {
		
		ResponseEntity<UsuarioResponseRest> response = service.crearUsuario(request);
		return response;
	}
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<UsuarioResponseRest> editarUsuario(@RequestBody Usuarios request, @PathVariable Long id) {
		
		ResponseEntity<UsuarioResponseRest> response = service.editarUsuario(request, id);
		return response;
	}
	
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<UsuarioResponseRest> eliminarUsuario(@PathVariable Long id) {
		
		ResponseEntity<UsuarioResponseRest> response = service.eliminarUsuario(id);
		return response;
	}
}
