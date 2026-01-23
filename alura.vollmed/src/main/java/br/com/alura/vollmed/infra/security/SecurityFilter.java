package br.com.alura.vollmed.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.vollmed.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository repository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		System.out.println("FILTRO CHAMADO!!!!");
		var tokenJWT = recuperarToken(request);
		System.out.println(tokenJWT);
		
		if (tokenJWT != null) {
			
			var subject = tokenService.getSubject(tokenJWT);
			var usuario = repository.findByLogin(subject);
			var authentication = new UsernamePasswordAuthenticationToken(usuario,null, usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			System.out.println(subject);	
		}
		
		filterChain.doFilter(request, response); //Chamar os próximos filtros na aplicação
	}

	private String recuperarToken(HttpServletRequest request) {

		var authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", ""); //Retirar o prefixo Bearer
		}
		
		return null;
	}
}
