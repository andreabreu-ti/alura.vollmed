package br.com.alura.vollmed.domain.medico;

import br.com.alura.vollmed.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
		
		@NotNull
		Long id, 
		String nome, 
		String telefone, 
		DadosEndereco endereco) {

}
