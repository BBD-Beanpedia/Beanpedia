package net.ryan.config;

import net.ryan.GitHubTokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final GitHubTokenValidatorFilter gitHubTokenValidatorFilter;

    public SecurityConfig(GitHubTokenValidatorFilter gitHubTokenValidatorFilter) {
        this.gitHubTokenValidatorFilter = gitHubTokenValidatorFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers(HttpMethod.GET, "/beans/all/**").permitAll()
                                .anyRequest().permitAll()
                        //.anyRequest().authenticated()
                )
                .addFilterBefore(gitHubTokenValidatorFilter, BasicAuthenticationFilter.class);

        return http.build();
    }
}