package com.colegiocursosms.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Configuración de seguridad permisiva SOLO para el perfil de desarrollo ("dev").
 * Permite todas las peticiones y simula un usuario autenticado.
 */
@Configuration
@Profile("dev") // <-- Esta clase solo se activa con el perfil "dev"
public class DevSecurityConfig {

      @Bean
      public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
            return http
                  .csrf(ServerHttpSecurity.CsrfSpec::disable)
                  .authorizeExchange(exchanges -> exchanges.anyExchange().permitAll()) // 1. Permite todo
                  .securityContextRepository(NoOpServerSecurityContextRepository.getInstance()) // 2. Desactiva la gestión de sesión
                  .build();
      }
}