package ma.enset.hopitalTP3.security;

import ma.enset.hopitalTP3.security.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private UserDetailServiceImpl userDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll();
        http.authorizeHttpRequests().requestMatchers("/webjars/**").permitAll();

        http.rememberMe();
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/notAuthorized");
        http.userDetailsService(userDetailService);
        return http.build();
    }

    //@Bean
public InMemoryUserDetailsManager InMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password(passwordEncoder.encode("1234"))
                        .roles("USER")
                        .build(),
                User.withUsername("admin")
                        .password(passwordEncoder.encode("1234"))
                        .roles("USER", "ADMIN")
                        .build()
        );
    }
    @Bean
    public JdbcUserDetailsManager JdbcUserDetailsManager(DataSource dataSource) {
        return new jdbcUserDetailsManager(dataSource);
    }

}
