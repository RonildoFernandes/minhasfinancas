package com.nivelamento.minhasfinancas.api.Controler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nivelamento.minhasfinancas.api.dto.UsuarioDTO;
import com.nivelamento.minhasfinancas.model.Entity.Usuario;
import com.nivelamento.minhasfinancas.model.Excepition.ErroAuteticacao;
import com.nivelamento.minhasfinancas.model.Excepition.RegraNegocioExcepition;
import com.nivelamento.minhasfinancas.model.Service.UsuariosService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControler {
	private UsuariosService service;
	public UsuarioControler(UsuariosService service) {
		this.service = service;
	}
	
	
@PostMapping("/autenticar")	
public ResponseEntity autenticar(@RequestBody UsuarioDTO dto) {
	try {
	Usuario	 autenticado = service.autenticar(dto.getEmail(), dto.getSenha());
	return ResponseEntity.ok(autenticado);
	
	} catch (ErroAuteticacao e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
}
	
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
		Usuario usuario = Usuario.builder()
				.nome(dto.getNome()).email(dto.getEmail())
				.senha(dto.getSenha()).build();
	try {
		Usuario usuarioSalvo = service.salvarUsuario(usuario);
	
		return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
	} catch (RegraNegocioExcepition e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}	
	}
}
