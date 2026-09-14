package com.jpgroups.springsecuritybasics.SpringSecurity.configuration;

import com.jpgroups.springsecuritybasics.SpringSecurity.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
*
*This Configuration class is created by Krishna,
* to secure the Passwords using the BCryptPasswordEncoder Hashing Algorithm!!!
*
**/

@Configuration
public class SecurityConfig
{
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(CustomUserDetailService userDetailService,PasswordEncoder passwordEncoder)
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   DaoAuthenticationProvider provider,
                                                   JwtAuthenticationConverter jwtAuthenticationConverter)
    {
        httpSecurity.csrf(csrf -> csrf.disable())
                .authenticationProvider(provider)
                //.formLogin(Customizer.withDefaults()) // --- NOT NEEDED FOR JWT TOKEN
                //.httpBasic(Customizer.withDefaults()) // --- NOT NEEDED FOR JWT TOKEN
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/users/register",
                                        "/auth/login").permitAll()
                                .anyRequest().authenticated())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //for BASIC AUTH = IF_REQUIRED
                )
                .oauth2ResourceServer(oauth->
                        oauth.jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));

        return httpSecurity.build();
    }

    @Bean
    public SecretKey jwtSecretKey(
            @Value("${jwt.secret}") String key
    )
    {
        byte[] decodedKey = Base64.getDecoder().decode(key);
        return new SecretKeySpec(decodedKey, "HmacSHA256");
    }

    @Bean
    public AuthenticationManager authenticationManager(DaoAuthenticationProvider daoAuthenticationProvider)
    {
        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter()
    {
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthoritiesClaimName("authorities");
        authoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean // to sign token
    public JwtEncoder jwtEncoder(SecretKey secretKey)
    {
        return NimbusJwtEncoder
                .withSecretKey(secretKey)
                .algorithm(MacAlgorithm.HS256)
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder(SecretKey secretKey,
                                 @Value("${jwt.issuer}") String issuer )
    {
        NimbusJwtDecoder decoder = NimbusJwtDecoder
                .withSecretKey(secretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
        decoder.setJwtValidator(JwtValidators.createDefaultWithIssuer(issuer));
        return decoder;
    }

}
