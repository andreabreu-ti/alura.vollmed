package br.com.alura.vollmed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.vollmed.endereco.Medico;
import br.com.alura.vollmed.medico.DadosAtualizacaoMedico;
import br.com.alura.vollmed.medico.DadosCadastroMedico;
import br.com.alura.vollmed.medico.DadosListagemMedico;
import br.com.alura.vollmed.medico.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository repository;

	/**
	 * Método cadastrar
	 * 
	 * @param dados
	 */

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {

		repository.save(new Medico(dados)); // Criar o construtor na classe Médico
	}

	/**
	 * Método para listar médicos
	 * 
	 * @return
	 */
	@GetMapping
	public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {

		return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
	}

	/**
	 * Método de atualizar médico
	 * 
	 * @param dados
	 */
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {

		var medico = repository.getReferenceById(dados.id()); //Recuperar o id do banco de dados
		medico.atualizarInformacoes(dados);
	}

	/**
	 * Método deletar médico
	 * 
	 * @param id
	 */
	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		
		//repository.deleteById(id); deletar fisicamente
		
		var medico = repository.getReferenceById(id); //Recuperar o id do banco de dados
		medico.excluir(); //deletar logicamente
		
	}

}
