package med.voll.api.domain.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.ValidationException;
import lombok.experimental.var;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsultas;
import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;

@Service
public class AgendaDeConsultas {

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private List<ValidadorAgendamentoDeConsultas> validadores;
	
	@Autowired
	private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

	public void cancelar(DadosCancelamentoConsulta dados) {
		if (!consultaRepository.existsById(dados.idConsulta())) {
			throw new ValidacaoException("Id da consulta informado não existe!");
		}
		validadoresCancelamento.forEach(v -> v.validar(dados));
		var consulta = consultaRepository.getReferenceById(dados.idConsulta());
		//consulta.cancelar(dados.motivo());
	}
	
	
	public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
		if (!pacienteRepository.existsById(dados.idPaciente())) {
			throw new ValidacaoException("Id do paciente informado não existe!!");
		}
		if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
			throw new ValidacaoException("Id do médico informado não existe!!");
		}
		validadores.forEach(v -> v.validar(dados));
		var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
		var medico = escolherMedico(dados);
		if (medico == null) {
			throw new ValidacaoException("Não existe médico disponível nesta data!!");
		}
		var consulta = new Consulta(null, medico, paciente, dados.data());
		consultaRepository.save(consulta);
		return new DadosDetalhamentoConsulta(consulta);
	}

	private Medico escolherMedico(DadosAgendamentoConsulta dados) {
		if (dados.idMedico() != null) {
			return medicoRepository.getReferenceById(dados.idMedico());
		}
		if (dados.especialidade() == null) {
			throw new ValidationException("Especialidade é obrigatória quando o médico não for escolhido!!");
		}
		return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
	}

}
