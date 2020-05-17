package com.nivelamento.minhasfinancas.model.Repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.nivelamento.minhasfinancas.model.Entity.Usuario;
import com.nivelamento.minhasfinancas.model.Excepition.RegraNegocioExcepition;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository repo;
	
	@Autowired
	TestEntityManager enti;
	
	
	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		//cenario
		Usuario usuario = criarUsuario();
		enti.persist(usuario);
		//ação
		
		
		boolean result = repo.existsByEmail("usuario@email.com");
		
		
		//verificação
		Assertions.assertThat(result).isTrue();
		
	}
	
	@Test
	public  void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
		//cenário
		
		
		//ação
		boolean result = repo.existsByEmail("usuario@email.com");
		
		//verificação;
		Assertions.assertThat(result).isFalse();
	}
	
	@Test
	public void devePersistirUmusuarioNaBaseDeDados() {
		//cenario
		
		Usuario usuario = criarUsuario();
		
		//açao
		Usuario salvo = repo.save(usuario);
		
		//verificação
		Assertions.assertThat(salvo.getId()).isNotNull();
	}
	
	@Test
	public void deveBuscarUmUsuarioPorEmail() {
		//cenario
		Usuario usuario = criarUsuario();
		enti.persist(usuario);
		//verificação
		Optional<Usuario> result = repo.findByEmail("usuario@email.com");
		Assertions.assertThat(result.isPresent()).isTrue();
	
	}
	@Test
	public void deveRetornarVazioQuandoNaoExistenaBase() {
		
		//verificação
		Optional<Usuario> result = repo.findByEmail("usuario@email.com");
		Assertions.assertThat(result.isPresent()).isFalse();
	
	}
	

	public static Usuario criarUsuario() {
		return  Usuario.builder().nome("usuario")
			   .email("usuario@email.com").senha("senha").build();
		
	}
}
