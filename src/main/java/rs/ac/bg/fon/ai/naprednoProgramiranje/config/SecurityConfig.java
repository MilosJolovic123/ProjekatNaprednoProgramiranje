package rs.ac.bg.fon.ai.naprednoProgramiranje.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import rs.ac.bg.fon.ai.naprednoProgramiranje.UserDetailService.JpaUserDetailsService;
/**
 * A class that represents custom configuration of Spring Security.
 * @author milos jolovic
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    /**
     * Reference to the custom-made UserDetailService.
     */
     private final JpaUserDetailsService userDetailsService;
    /**
     * Constructor injection of JPAUserDetailsService.
     * @param userDetailsService reference to userDetailsService
     */
     public SecurityConfig(JpaUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    /**
     * Custom made Filter chain that ensures method endpoint security.
     * @param http parameter that is to be configured.
     * @return Built http parameter.
     * @throws Exception class if endpoint not found.
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/**"))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated()


                )
                .userDetailsService(userDetailsService)
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }
    /**
     * Custom PasswordEncoder for encoding passwords before persisting them in H2 db.
     * @return BCryptPasswordEncoder
     */
     @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
