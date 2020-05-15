package com.nivelamento.minhasfinancas.model.Service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nivelamento.minhasfinancas.model.Entity.Usuario;
import com.nivelamento.minhasfinancas.model.Excepition.ErroAuteticacao;
import com.nivelamento.minhasfinancas.model.Excepition.RegraNegocioExcepition;
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
		 Optional<Usuario> usuario = repository.findByEmail(email);
		if(!usuario.isPresent()) {
			throw new ErroAuteticacao("não Encontrado");
		}
		if(!usuario.get().getSenha().equals(senha)) {
			throw new ErroAuteticacao("não Encontrado");
		}else
		 return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioExcepition("já existe um usuário com esse email.");
		}
	}

}
