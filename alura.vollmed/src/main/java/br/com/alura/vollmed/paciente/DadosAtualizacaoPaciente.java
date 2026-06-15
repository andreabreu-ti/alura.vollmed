package br.com.alura.vollmed.paciente;

import br.com.alura.vollmed.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(
		
		 @NotNull
	        Long id,
	        String nome,
	        String telefone,
	        DadosEndereco endereco
		) {
}
