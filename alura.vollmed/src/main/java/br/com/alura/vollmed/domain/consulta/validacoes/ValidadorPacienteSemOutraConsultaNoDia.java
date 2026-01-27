package br.com.alura.vollmed.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alura.vollmed.domain.ValidacaoException;
import br.com.alura.vollmed.domain.consulta.ConsultaRepository;
import br.com.alura.vollmed.domain.consulta.DadosAgendamentoConsulta;

public class ValidadorPacienteSemOutraConsultaNoDia {

	@Autowired
	private ConsultaRepository repository;

	public void validar(DadosAgendamentoConsulta dados) {

		var primeiroHorario = dados.data().withHour(7);
		var ultimoHorario = dados.data().withHour(18);
		var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(),
				primeiroHorario, ultimoHorario);

		if (pacientePossuiOutraConsultaNoDia) {
			throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia.");
		}
	}
}
