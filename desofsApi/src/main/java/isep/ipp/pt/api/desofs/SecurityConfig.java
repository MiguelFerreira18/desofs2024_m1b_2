package isep.ipp.pt.api.desofs;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import isep.ipp.pt.api.desofs.Model.UserModel.Role;
import isep.ipp.pt.api.desofs.Repository.Repo.UserRepo;
import isep.ipp.pt.api.desofs.Service.UserService.UserService;
import isep.ipp.pt.api.desofs.Service.UserService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Value("${jwt.public.key}")
    private RSAPublicKey rsaPublicKey;

    @Value("${jwt.private.key}")
    private RSAPrivateKey rsaPrivateKey;

    @Value("${springdoc.api-docs.path}")
    private String restApiDocPath;

    @Value("${springdoc.swagger-ui.path}")
    private String swaggerPath;

    @Autowired
    private UserServiceImpl userService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // TODO ADICIONAR PERMISSÕES À MEDIDA QUE SE VAI FAZENDO O WEBSITE


        // Enable CORS and disable CSRF
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                        .httpStrictTransportSecurity(hsts -> hsts
                                .includeSubDomains(true)
                                .preload(true)
                                .maxAgeInSeconds(31536000)
                        )
                        .xssProtection(xss -> xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK))
                        .referrerPolicy(referrerPolicy -> referrerPolicy.policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN))
                )
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOriginPatterns(Arrays.asList("*"));
                    config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "OPTIONS","PATCH"));
                    config.setAllowedHeaders(Arrays.asList("*"));
                    config.setExposedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .csrf(Customizer.withDefaults()).csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.ignoringRequestMatchers("/auth/public/**"))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/auth/public/signup").permitAll()
                                .requestMatchers("/auth/public/login").permitAll()
                                .requestMatchers("/pacote/all").permitAll()
                                .requestMatchers("/pacote/get/**").permitAll()
                                .requestMatchers("/pacote/**").hasRole(Role.Admin)
                                .requestMatchers("/pacote/**").hasRole(Role.GestorFicheiros)
                                .requestMatchers("/review/all").permitAll()
                                .requestMatchers("/review/pacote/**").permitAll()
                                .requestMatchers("/review/**").authenticated()
                                .requestMatchers("/tipoPacote/**").authenticated()
                                .requestMatchers("/encomenda/**").authenticated()
                                .requestMatchers("/user/info/**").authenticated()
                                .requestMatchers("/user/delete/data").authenticated()
                                .requestMatchers("/tipoReceita/**").authenticated()
                                .requestMatchers("/receita/**").authenticated()
                                .requestMatchers("/api-docs/**").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()

                )
                .httpBasic(Customizer.withDefaults()).oauth2ResourceServer((oauth) -> oauth.jwt(Customizer.withDefaults()))
                .authenticationProvider(authenticationProvider());
        return http.build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }


    @Bean
    UserDetailsService userDetailsService() {
        return userService::findByUsername;
    }


    @Bean
    public JwtEncoder jwtEncoder() {
        final JWK jwk = new RSAKey.Builder(this.rsaPublicKey).privateKey(this.rsaPrivateKey).build();
        final JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.rsaPublicKey).build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        final JwtGrantedAuthoritiesConverter jwtGrantedAuthenticationConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthenticationConverter.setAuthoritiesClaimName("roles");
        jwtGrantedAuthenticationConverter.setAuthorityPrefix("ROLE_");

        final JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthenticationConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.addAllowedMethod("*");
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowCredentials(true);
//        configuration.addExposedHeader(HttpHeaders.AUTHORIZATION);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }
}