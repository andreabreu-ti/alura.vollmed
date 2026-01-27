package br.com.alura.vollmed.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.vollmed.domain.ValidacaoException;
import br.com.alura.vollmed.domain.consulta.DadosAgendamentoConsulta;
import br.com.alura.vollmed.domain.medico.MedicoRepository;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private MedicoRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		
		//Escolha do médico opcional
		if (dados.idMedico() == null) {
			return;
		}
		
		var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
		if (!medicoEstaAtivo) {
			
			throw new ValidacaoException("Consulta nao pode ser agenda com médico excluído");
		}
	}
	
}
