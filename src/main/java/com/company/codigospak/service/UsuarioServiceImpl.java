package com.company.codigospak.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.codigospak.perfiles.dao.IUsuarioDao;
import com.company.codigospak.response.UsuarioResponseRest;
import com.company.codigospak.perfiles.Usuarios;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<UsuarioResponseRest> buscarUsuario() {
		
		log.info("Inicio metodo Buscar Usuarios()");
		
		UsuarioResponseRest response = new UsuarioResponseRest();
		
		try {
			
			List<Usuarios> usuarios = (List<Usuarios>) usuarioDao.findAll();
			
			response.getUsuarioResponse().setUsuarios(usuarios);
			
			response.setMetadata("Respuesta OK", "00", "Respuesta Exitosa");
			
		} catch(Exception e) {
			
			response.setMetadata("Respuesta NOK", "-1", "Respuesta Incorrecta");
			log.error("Error al consultar Usuarios: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<UsuarioResponseRest> (response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<UsuarioResponseRest> (response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<UsuarioResponseRest> buscarPorId(Long id) {
		
		log.info("Inicio metodo Buscar por ID");
		
		UsuarioResponseRest response = new UsuarioResponseRest();
		List<Usuarios> list = new ArrayList<>();
		
		try {
			
			Optional<Usuarios> usuarios = usuarioDao.findById(id);
			
			if (usuarios.isPresent()) {
				list.add(usuarios.get());
				response.getUsuarioResponse().setUsuarios(list);
				
			} else {
				log.error("Error en Consulta Usuarios");
				response.setMetadata("Respuesta NOK", "-1", "Usuario no encontrado");
				return new ResponseEntity<UsuarioResponseRest> (response, HttpStatus.NOT_FOUND);
			}
			
		} catch(Exception e) {
			log.error("Error en Consulta Usuarios");
			response.setMetadata("Respuesta NOK", "-1", "Usuario no encontrado");
			return new ResponseEntity<UsuarioResponseRest> (response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetadata("Respuesta OK", "00", "Respuesta Exitosa");
		
		return new ResponseEntity<UsuarioResponseRest> (response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<UsuarioResponseRest> crearUsuario(Usuarios usuarios) {
		
		log.info("Inicio metodo Crear Usuarios");
		
		UsuarioResponseRest response = new UsuarioResponseRest();
		List<Usuarios> list = new ArrayList<>();
		
		try {
			
			Usuarios usuarioGuardado = usuarioDao.save(usuarios);
			
			if(usuarioGuardado != null ) {
				list.add(usuarios);
				response.getUsuarioResponse().setUsuarios(list);				
			} else {
				log.error("Error en Creacion de Usuario");
				response.setMetadata("Respuesta NOK", "-1", "Usuario no guardado");
				return new ResponseEntity<UsuarioResponseRest> (response, HttpStatus.BAD_REQUEST);
			}
			
		} catch(Exception e) {
			log.error("Error en Creacion de Usuario");
			response.setMetadata("Respuesta NOK", "-1", "Usuario no encontrado");
			return new ResponseEntity<UsuarioResponseRest> (response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetadata("Respuesta OK", "00", "Respuesta Exitosa");
		
		return new ResponseEntity<UsuarioResponseRest> (response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<UsuarioResponseRest> editarUsuario(Usuarios usuarios, Long id) {
		
		log.info("Inicio metodo Editar Usuarios");
		
		UsuarioResponseRest response = new UsuarioResponseRest();
		List<Usuarios> list = new ArrayList<>();
		
		try {
			
			Optional<Usuarios> buscarUsuarios = usuarioDao.findById(id);
			
			if(buscarUsuarios.isPresent()) {
				buscarUsuarios.get().setNombre(usuarios.getNombre());
				buscarUsuarios.get().setApellido(usuarios.getApellido());
				buscarUsuarios.get().setCorreo(usuarios.getCorreo());
				
				Usuarios actualizarUsuarios = usuarioDao.save(buscarUsuarios.get());
				
				if(actualizarUsuarios != null) {
					response.setMetadata("Respuesta Ok", "00", "Usuario Editado");
					list.add(actualizarUsuarios);
					response.getUsuarioResponse().setUsuarios(list);
				} else {
					log.error("Error en Actualizacion de Usuario");
					response.setMetadata("Respuesta NOK", "-1", "Usuario no Actualizado");
					return new ResponseEntity<UsuarioResponseRest> (response, HttpStatus.BAD_REQUEST);
				}
				
			} else {
				log.error("Error en Actualizacion de Usuario");
				response.setMetadata("Respuesta NOK", "-1", "Usuario no Actualizado");
				return new ResponseEntity<UsuarioResponseRest> (response, HttpStatus.NOT_FOUND);
			}
			
		} catch(Exception e) {
			log.error("Error al Editar Usuario", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta Nok", "-1", "Usuario no Actualizado");
			return new ResponseEntity<UsuarioResponseRest> (response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<UsuarioResponseRest> (response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<UsuarioResponseRest> eliminarUsuario(Long id) {
		
		log.info("Inicio metodo Eliminar Usuarios");
		
		UsuarioResponseRest response = new UsuarioResponseRest();
		
		try {
			usuarioDao.deleteById(id);
			response.setMetadata("Respuesta Ok", "00", "Usuario Eliminado");
			
		} catch(Exception e) {
			log.error("Error al Eliminar Usuario", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta Nok", "-1", "Usuario no Eliminado");
			return new ResponseEntity<UsuarioResponseRest> (response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		return new ResponseEntity<UsuarioResponseRest> (response, HttpStatus.OK);	
	}		
}
