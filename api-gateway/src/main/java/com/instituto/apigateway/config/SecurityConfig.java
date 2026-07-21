package com.instituto.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // Los tres roles pueden consultar cursos
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/cursos/**"
                        ).hasAnyRole(
                                "ALUMNO",
                                "DOCENTE",
                                "ADMINISTRADOR"
                        )

                        // Docente y administrador pueden registrar
                        // o actualizar cursos
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/cursos/**"
                        ).hasAnyRole(
                                "DOCENTE",
                                "ADMINISTRADOR"
                        )

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/cursos/**"
                        ).hasAnyRole(
                                "DOCENTE",
                                "ADMINISTRADOR"
                        )

                        // Solamente el administrador elimina cursos
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/cursos/**"
                        ).hasRole("ADMINISTRADOR")

                        // Gestión de alumnos
                        .requestMatchers(
                                "/api/alumnos/**"
                        ).hasRole("ADMINISTRADOR")

                        // Gestión de docentes
                        .requestMatchers(
                                "/api/docentes/**"
                        ).hasRole("ADMINISTRADOR")

                        // Matrículas
                        .requestMatchers(
                                "/api/matriculas/**"
                        ).hasAnyRole(
                                "ALUMNO",
                                "ADMINISTRADOR"
                        )

                        .anyRequest().authenticated()
                )

                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(
                                        jwtAuthenticationConverter()
                                )
                        )
                );

        return http.build();
    }

    @Bean
    public Converter<Jwt, AbstractAuthenticationToken>
    jwtAuthenticationConverter() {

        JwtGrantedAuthoritiesConverter scopeConverter =
                new JwtGrantedAuthoritiesConverter();

        JwtAuthenticationConverter authenticationConverter =
                new JwtAuthenticationConverter();

        // El nombre del usuario autenticado será alumno1,
        // admin1, docente1, etc.
        authenticationConverter.setPrincipalClaimName(
                "preferred_username"
        );

        authenticationConverter.setJwtGrantedAuthoritiesConverter(
                jwt -> {

                    Collection<GrantedAuthority> authorities =
                            new ArrayList<>();

                    // Conserva los scopes propios de OAuth 2.0
                    Collection<GrantedAuthority> scopeAuthorities =
                            scopeConverter.convert(jwt);

                    if (scopeAuthorities != null) {
                        authorities.addAll(scopeAuthorities);
                    }

                    // Lee los roles generados por Keycloak
                    Map<String, Object> realmAccess =
                            jwt.getClaimAsMap("realm_access");

                    if (realmAccess == null) {
                        return authorities;
                    }

                    Object rolesObject = realmAccess.get("roles");

                    if (rolesObject instanceof Collection<?> roles) {

                        roles.stream()
                                .map(Object::toString)
                                .map(role ->
                                        new SimpleGrantedAuthority(
                                                "ROLE_" + role
                                        )
                                )
                                .forEach(authorities::add);
                    }

                    return authorities;
                }
        );

        return authenticationConverter;
    }
}