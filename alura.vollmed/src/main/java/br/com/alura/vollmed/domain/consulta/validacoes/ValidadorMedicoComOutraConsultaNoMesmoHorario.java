package br.com.alura.vollmed.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.vollmed.domain.ValidacaoException;
import br.com.alura.vollmed.domain.consulta.ConsultaRepository;
import br.com.alura.vollmed.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private ConsultaRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		
		var medicoPossuiOutraConsultaNoMesmoHorairo = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
		if (medicoPossuiOutraConsultaNoMesmoHorairo) {
			
			throw new ValidacaoException("Médico já possui outra consuta agendada nesse mesmo horário");
		}
	}
}
