//Concentrar as configurações de segurança do Spring Security 

package med.voll.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
	
	@Autowired
	private SecurityFilter securityFilter;

	//Configuração do processo de autenticação StateFull --> STATELESS
	//É importante determinar a ordem de execução dos filtros
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf().disable() // Desabilitar a proteção contra ataques CSRF-Cross-Site Request Forgery o token já faz essa proteção
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// Configuração para ser Staytless
				.and().authorizeHttpRequests()
				.requestMatchers(HttpMethod.POST, "/login").permitAll()
				.anyRequest().authenticated()
				.and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)  
				.build();
		
	}
	
	/*	controle de acesso por url -- Perfil de Acesso
	  
	 Na aplicação utilizada no curso não teremos perfis de acessos distintos para os usuários. Entretanto, 
	 esse recurso é utilizado em algumas aplicações e podemos indicar ao Spring Security que determinadas 
	 URLs somente podem ser acessadas por usuários que possuem um perfil específico.
	 Por exemplo, suponha que em nossa aplicação tenhamos um perfil de acesso chamado de ADMIN, 
	 sendo que somente usuários com esse perfil possam excluir médicos e pacientes. Podemos indicar ao 
	 Spring Security tal configuração alterando o método securityFilterChain, na classe SecurityConfigurations, 
	 da seguinte maneira:
	 
	 Repare que no código anterior foram adicionadas duas linhas, indicando ao Spring Security que as requisições 
	 do tipo DELETE para as URLs /medicos e /pacientes somente podem ser executadas por usuários autenticados e cujo 
	 perfil de acesso seja ADMIN.
	 	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    return http.csrf().disable()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and().authorizeHttpRequests()
	        .requestMatchers(HttpMethod.POST, "/login").permitAll()
	        .requestMatchers(HttpMethod.DELETE, "/medicos").hasRole("ADMIN")
	        .requestMatchers(HttpMethod.DELETE, "/pacientes").hasRole("ADMIN")
	        .anyRequest().authenticated()
	        .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
	        .build();
	}*/
	
	/*
	 * controle de acesso por anotações
	 * 
	 * Outra maneira de restringir o acesso a determinadas funcionalidades, com base no perfil dos usuários, 
	 * é com a utilização de um recurso do Spring Security conhecido como Method Security, que funciona com a 
	 * utilização de anotações em métodos:
	 * 
	 * 
	 * 	@GetMapping("/{id}")
		@Secured("ROLE_ADMIN")
		public ResponseEntity detalhar(@PathVariable Long id) {
    		var medico = repository.getReferenceById(id);
    		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
		}
	 * No exemplo de código anterior o método foi anotado com @Secured("ROLE_ADMIN"), para que apenas usuários com o 
	 * perfil ADMIN possam disparar requisições para detalhar um médico. 
	 * A anotação @Secured pode ser adicionada em métodos individuais ou mesmo na classe, 
	 * que seria o equivalente a adicioná-la em todos os métodos.
	 * Atenção! Por padrão esse recurso vem desabilitado no spring Security, sendo que para o utilizar devemos adicionar 
	 * a seguinte anotação na classe Securityconfigurations do projeto:
	 * 
	 * @EnableMethodSecurity(securedEnabled = true)
	 * 
	 * 
	 * Você pode conhecer mais detalhes sobre o recurso de method security na documentação do Spring Security, 
	 * disponível em: https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html
	 * 
	 * 
	 * */
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
