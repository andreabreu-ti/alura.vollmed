package med.voll.api.infra.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // Componente genérico
public class SecurityFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		var tokenJWT = recuperarToken(request);
		
		System.out.println(tokenJWT);
		
		// Necessário para chamar os próximos filtros na aplicação,
		// Garantir que a linha seja executada
		filterChain.doFilter(request, response);

	}

	private String recuperarToken(HttpServletRequest request) {

		var authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader == null) {
			throw new RuntimeException("Token JWT não enviado no cabeçalho Authorization!");
		}

		return authorizationHeader.replace("Bearer", "");
	}

}
