package com.nivelamento.minhasfinancas.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nivelamento.minhasfinancas.model.Entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {
	
	
	boolean existsByEmail(String email);
}
