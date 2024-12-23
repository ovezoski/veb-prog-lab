package com.finki.lab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final CustomUsernamePasswordAuthenticationProvider authProvider;


    public WebSecurityConfig(PasswordEncoder passwordEncoder, CustomUsernamePasswordAuthenticationProvider authProvider){
        this.passwordEncoder = passwordEncoder;
        this.authProvider = authProvider;
    }

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .authorizeHttpRequests((requests) ->
                        requests.requestMatchers("/", "/songs", "/artists", "/register", "/assets/**")
                                .permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest()
                                .authenticated()
                ).formLogin((form) -> form
                                .loginPage("/login")
                                .permitAll()
                                .failureUrl("/login?error=BadCredentials")
                                .defaultSuccessUrl("/songs", true)
                )
                .logout( (logout) -> logout
                        .logoutUrl("/logout")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/login")
                )
                .exceptionHandling((ex) ->  ex.accessDeniedPage("access_denied"))                ;

        return httpSecurity.build();
    }


    @Bean
    public UserDetailsService UserDetailsService(){


        UserDetails user1 = User.builder()
                .username("elena.atanasoska")
                .password(passwordEncoder.encode("ea"))
                .roles("USER")
                .build();


        UserDetails user2 = User.builder()
                .username("darko.sasanski")
                .password(passwordEncoder.encode("ds"))
                .roles("USER")
                .build();


        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();


        return new InMemoryUserDetailsManager(user1, user2, admin);
    }


    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }

}
