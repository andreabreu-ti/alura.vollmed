package br.com.alura.vollmed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.vollmed.medico.DadosCadastroMedico;
import br.com.alura.vollmed.medico.Medico;
import br.com.alura.vollmed.medico.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;

	@PostMapping
	public void cadastrar(@RequestBody DadosCadastroMedico dados) {

		repository.save(new Medico(dados));
	}
}
