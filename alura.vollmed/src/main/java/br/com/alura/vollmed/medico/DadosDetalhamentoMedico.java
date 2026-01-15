package br.com.alura.vollmed.medico;

import br.com.alura.vollmed.endereco.Endereco;
import br.com.alura.vollmed.endereco.Medico;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone,
		Especialidade especialidade, Endereco endereco) {

	public DadosDetalhamentoMedico(Medico medico) {

		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(),
				medico.getEspecialidade(), medico.getEndereco());

	}

}
