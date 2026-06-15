package br.com.alura.vollmed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.vollmed.medico.DadosCadastroMedico;
import br.com.alura.vollmed.medico.DadosListagemMedico;
import br.com.alura.vollmed.medico.Medico;
import br.com.alura.vollmed.medico.MedicoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository repository;

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {

		repository.save(new Medico(dados));
	}

	@GetMapping
	public Page<DadosListagemMedico> listar(Pageable paginacao) {

		return repository.findAll(paginacao).map(DadosListagemMedico::new);
	}
}
