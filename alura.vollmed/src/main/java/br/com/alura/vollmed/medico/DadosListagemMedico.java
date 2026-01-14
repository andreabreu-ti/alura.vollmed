package br.com.alura.vollmed.medico;

import br.com.alura.vollmed.endereco.Medico;

public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

	public DadosListagemMedico(Medico medico) {
		
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
	}
}
