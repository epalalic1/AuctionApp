package com.developer.auctionapp.configuration;

import com.developer.auctionapp.repository.UserRepository;
import com.developer.auctionapp.security.JWTAuthenticationFilter;
import com.developer.auctionapp.security.JwtAuthEntryPoint;
import com.developer.auctionapp.security.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <p>Class that contains all the data we need to include security to the project</p>
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtAuthEntryPoint jwtAuthEntryPoint;

    private UserDetailsService userDetailsService;

    private UserRepository userRepository;


    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, JwtAuthEntryPoint jwtAuthEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable()
                .and()
                .csrf().disable()
                .cors()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auctionapp/").permitAll()
                .antMatchers("/auctionapp/user/login").permitAll()
                .antMatchers("/auctionapp/user/register").permitAll()
                .antMatchers("/auctionapp/product/getAll").permitAll()
                .antMatchers("/auctionapp/product/getNewProducts").permitAll()
                .antMatchers("/auctionapp/product/getLastChanceProducts").permitAll()
                .antMatchers("/auctionapp/category/getAll").permitAll()
                .antMatchers("/auctionapp/image/getAll").permitAll()
                .antMatchers("/auctionapp/subcategory/getAll").permitAll()
                .antMatchers("/auctionapp/product/getProductFromId").permitAll()
                .antMatchers("/auctionapp/bid/getAll").permitAll()
                .antMatchers("/ws/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }
}
