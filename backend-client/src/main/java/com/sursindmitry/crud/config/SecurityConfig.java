package com.sursindmitry.crud.config;

import com.sursindmitry.crud.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)

            .cors(AbstractHttpConfigurer::disable)

            .authorizeHttpRequests(authorizeHttpRequests ->
                authorizeHttpRequests
                    .requestMatchers(
                        "/",
                        "/login",
                        "/logout",
                        "/v1/user/get-rest"
                    ).permitAll()

                    .requestMatchers(
                        "/v1/admin/**",
                        "/create-user",
                        "/read-users",
                        "/delete-user",
                        "/update-user"
                    ).hasRole("ADMIN")

                    .requestMatchers(
                        "/v1/user/profile",
                        "/profile"
                    ).hasAnyRole("USER", "ADMIN")

                    .requestMatchers("/v1/user/**").hasRole("USER")


                    .anyRequest().hasRole("ADMIN")
            )


            .formLogin(formLogin -> formLogin.loginPage("/login").permitAll())
            .logout(
                httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutSuccessUrl("/")
            );


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(customUserDetailsService);

        return authenticationProvider;
    }

}
