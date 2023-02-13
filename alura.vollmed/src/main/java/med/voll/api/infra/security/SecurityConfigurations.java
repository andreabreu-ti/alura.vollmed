//Concentrar as configurações de segurança do Spring Security 

package med.voll.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

	//Configuração do processo de autenticação StateFull --> STATELESS
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf().disable() // Desabilitar a proteção contra ataques CSRF-Cross-Site Request Forgery o token já faz essa proteção
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Configuração para ser Staytless
				.and().build();

	}
}
