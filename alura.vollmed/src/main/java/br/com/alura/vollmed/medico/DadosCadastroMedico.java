package br.com.alura.vollmed.medico;

import br.com.alura.vollmed.endereco.DadosEndereco;

public record DadosCadastroMedico(
		String nome, 
		String email, 
		String crm, 
		Especialidade especialidade,
		DadosEndereco endereco
	) {
}
