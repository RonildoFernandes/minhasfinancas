package com.nivelamento.minhasfinancas.model.Service;

import com.nivelamento.minhasfinancas.model.Entity.Usuario;

public interface UsuariosService {
	Usuario autenticar(String email, String senha);
	Usuario salvarUsuario(Usuario usuario);
	void validarEmail(String email);
}
