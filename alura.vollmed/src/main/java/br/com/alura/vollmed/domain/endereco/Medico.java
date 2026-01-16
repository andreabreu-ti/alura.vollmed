package br.com.alura.vollmed.domain.endereco;

import br.com.alura.vollmed.domain.medico.DadosAtualizacaoMedico;
import br.com.alura.vollmed.domain.medico.DadosCadastroMedico;
import br.com.alura.vollmed.domain.medico.Especialidade;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Atributos do lombook. geração automática por anotações
 * @Getter					
 * @NoArgsConstructor
 * @AllArgsConstructor
 * @EqualsAndHashCode(of = "id"), gerar apenas para o ID
 */

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter					
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String email;
	private String telefone;
	private String crm;

	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;

	@Embedded
	private Endereco endereco;
	
	private Boolean ativo;
	
	public Medico(DadosCadastroMedico dados) {
		this.ativo = true;
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
		this.crm = dados.crm();
		this.especialidade = dados.especialidade();
		this.endereco = new Endereco(dados.endereco()); //Criar o construtor na classe endereço
	}

	public void atualizarInformacoes(DadosAtualizacaoMedico dados) {

		if (dados.nome() != null) {
			this.nome = dados.nome();
		}
		if(dados.telefone() != null) {
			this.telefone = dados.telefone();
		}
		if (dados.endereco() != null) {
			this.endereco.atualizarInformacoes(dados.endereco()); 
		}
		
	}

	public void excluir() {
		
		this.ativo = false;
	}

}
