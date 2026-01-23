package br.com.alura.vollmed.domain.paciente;

import br.com.alura.vollmed.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente( @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {

}
