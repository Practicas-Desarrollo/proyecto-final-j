package com.concell.system.configuraciones.seguridad;

import com.concell.system.configuraciones.CustomCorsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

  private final AuthenticationProvider authenticationProvider;
  private final JwtAutenticacionFilter jwtAuthenticationFilter;
  private final CustomCorsConfig customCorsConfiguration;

  public SecurityConfig(AuthenticationProvider authenticationProvider,
                        JwtAutenticacionFilter jwtAutenticacionFilter,
                        CustomCorsConfig customCorsConfiguration) {
    this.authenticationProvider = authenticationProvider;
    this.jwtAuthenticationFilter = jwtAutenticacionFilter;
    this.customCorsConfiguration = customCorsConfiguration;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .cors(cors ->
                    cors.configurationSource(customCorsConfiguration))
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                    requests ->
                      requests.antMatchers(
                              "/auth/**",
                              "/v2/api-docs",
                              "/v3/api-docs",
                              "/v3/api-docs/**",
                              "/swagger-resources/",
                              "/swagger-resources/**",
                              "/configuration/ui",
                              "/configuration/security",
                              "/swagger-ui/**",
                              "/swagger-ui.html",
                              "/webjars/**"
                      ).permitAll()
                              .anyRequest()
                                  .authenticated()
            )
            .sessionManagement(session ->
                    session.sessionCreationPolicy(STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(exceptions ->
                    exceptions.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            );

    return http.build();
  }
}
