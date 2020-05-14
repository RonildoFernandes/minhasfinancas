package com.nivelamento.minhasfinancas.model.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nivelamento.minhasfinancas.model.Entity.Usuario;
import com.nivelamento.minhasfinancas.model.Repository.UsuarioRepository;
import com.nivelamento.minhasfinancas.model.Service.UsuariosService;
@Service
public class UsuarioServiceImp implements UsuariosService {
	
	private UsuarioRepository repository;
	
	
	@Autowired
	public UsuarioServiceImp(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario salvarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validarEmail(String email) {
		repository.;
		
	}

}
