package med.voll.api.paciente;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

public record DadosListagemPaciente(String nome, String email, String cpf) {
	
	public DadosListagemPaciente(Paciente paciente) {
		this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
	}

	
}
