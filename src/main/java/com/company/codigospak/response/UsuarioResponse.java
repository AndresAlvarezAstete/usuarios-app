package com.company.codigospak.response;

import java.util.List;

import com.company.codigospak.perfiles.Usuarios;

public class UsuarioResponse {
	
	private List<Usuarios> usuarios;

	public List<Usuarios> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuarios> usuarios) {
		this.usuarios = usuarios;
	}
}
