package com.nivelamento.minhasfinancas.model.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nivelamento.minhasfinancas.model.Entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {
	Optional<Usuario> findByEmail(String email);
	
}
