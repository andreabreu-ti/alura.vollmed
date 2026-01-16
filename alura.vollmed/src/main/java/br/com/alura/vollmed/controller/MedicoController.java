package br.com.alura.vollmed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.vollmed.domain.endereco.Medico;
import br.com.alura.vollmed.domain.medico.DadosAtualizacaoMedico;
import br.com.alura.vollmed.domain.medico.DadosCadastroMedico;
import br.com.alura.vollmed.domain.medico.DadosDetalhamentoMedico;
import br.com.alura.vollmed.domain.medico.DadosListagemMedico;
import br.com.alura.vollmed.domain.medico.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository repository;

	/**
	 * Método cadastrar - Código http 201
	 * 
	 * @param dados
	 */

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {

		// Código de Protócolo http 201
		var medico = new Medico(dados);
		repository.save(medico);
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
	}

	/**
	 * Método para listar médicos - Código http 200
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {

		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
		return ResponseEntity.ok(page);
	}

	/**
	 * Método de atualizar médico Código http 200
	 * 
	 * @param dados
	 */
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {

		var medico = repository.getReferenceById(dados.id()); // Recuperar o id do banco de dados
		medico.atualizarInformacoes(dados);

		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}

	/**
	 * Método deletar médico Código http 204
	 * 
	 * @param id
	 */
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {

		// repository.deleteById(id); deletar fisicamente

		var medico = repository.getReferenceById(id); // Recuperar o id do banco de dados
		medico.excluir(); // deletar logicamente

		return ResponseEntity.noContent().build();

	}

	/**
	 * Método detalhar médico por id - Código http 200
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {

		var medico = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));

	}

}
