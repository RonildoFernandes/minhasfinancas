package com.nivelamento.minhasfinancas.model.Service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nivelamento.minhasfinancas.model.Entity.Usuario;
import com.nivelamento.minhasfinancas.model.Excepition.ErroAuteticacao;
import com.nivelamento.minhasfinancas.model.Excepition.RegraNegocioExcepition;
import com.nivelamento.minhasfinancas.model.Repository.UsuarioRepository;
import com.nivelamento.minhasfinancas.model.Service.Impl.UsuarioServiceImp;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@SpyBean
	UsuarioServiceImp service;
	@MockBean
	UsuarioRepository repo;
	
	@Before
	/*public void setUp() {
		
		service = new UsuarioServiceImp(repo);
	}
	*/
	@Test
	public void deveAutenticarComsucesso() {
		//cenario
		String email = "email@email.com";
		String senha="senha";
		
		Usuario usuario = Usuario.builder().email(email).senha(senha).id(1l).build();
		Mockito.when(repo.findByEmail(email) ).thenReturn(Optional.of(usuario));
		
		Usuario result = service.autenticar(email, senha);
		//verificação
		
		Assertions.assertNotNull(result);
	}
	@Test
	public void deveLancarErroQuandoSenhaNaoBater() {
		Assertions.assertThrows(ErroAuteticacao.class, () -> 
		{
			//cenário
			String senha ="senha";
			Usuario usuario = Usuario.builder().email("email@email.com").senha(senha).build();
			Mockito.when(repo.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));
			
			//ação
			service.autenticar("email@email.com", "joia");	
			
			
		});
		
	}
	@Test	
	public void deveLancarerroSemUsuarioCadastrado() {
		Assertions.assertThrows(ErroAuteticacao.class, () -> 
		{
			//cenário
			Mockito.when(repo.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
			
			//ação
			service.autenticar("email@email.com", "senha");
			
		});
		
		}
	
	
	@Test
	public void deveValidarEmail() {
		//cenario
		
		Mockito.when(repo.existsByEmail(Mockito.anyString())).thenReturn(false);
		
		//acao
		service.validarEmail("email@email.com");
	}
	
	@Test
	public void deveLancarErroQuandoexistirEmailCadastrado() 
	{
		
		Assertions.assertThrows(RegraNegocioExcepition.class, () -> 
		{
			//cenario
			
			Mockito.when(repo.existsByEmail(Mockito.anyString())).thenReturn(true);
			
		
			//açao
			service.validarEmail("email@email.com");
		});
		
   
 }
	@Test
	public void deveSalvarUsuario() {
		//cenario
		Mockito.doNothing().when(service).validarEmail(Mockito.anyString());
		Usuario usuario = Usuario.builder().id(1l).nome("Ronildo").email("email@email.com").senha("senha").build();
		
		Mockito.when(repo.save(Mockito.any(Usuario.class))).thenReturn(usuario);
		
		
		//ação
		Usuario salvo = service.salvarUsuario(new Usuario());
		//verificação
		assertThat(salvo).isNotNull();
		assertThat(salvo.getId()).isEqualTo(1l);
		assertThat(salvo.getNome()).isEqualTo("Ronildo");
		assertThat(salvo.getEmail()).isEqualTo("email@email.com");
		assertThat(salvo.getSenha()).isEqualTo("senha");
	}

}
