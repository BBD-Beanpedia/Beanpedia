package net.ryan.config;


import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import net.ryan.config.RsaKeyProps;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final RsaKeyProps rsaKeyProps;

    public SecurityConfig(RsaKeyProps rsaKeyProps) {
        this.rsaKeyProps = rsaKeyProps;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/auth/token", "/beans/search/*", "/beans/all/*", "/beans/filter/*", "/beans/attributes/*").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(jwt -> jwt.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())))
                .build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeyProps.publicKey()).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {

        final JWK jwk = new RSAKey.Builder(rsaKeyProps.publicKey()).privateKey(rsaKeyProps.privateKey()).build();
        final ImmutableJWKSet<SecurityContext> jwkSet = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSet);
    }

}
