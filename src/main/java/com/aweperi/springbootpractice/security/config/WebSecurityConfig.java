package com.aweperi.springbootpractice.security.config;

import com.aweperi.springbootpractice.filter.CustomAuthenticationFilter;
import com.aweperi.springbootpractice.filter.CustomAuthorizationFilter;
import com.aweperi.springbootpractice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bcryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/swagger-ui.html");
        http.authorizeRequests().antMatchers(GET, "/api/v*/signup/**").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/v*/signup/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/v*/login").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/v*/login").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/v*/users/**").hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers(POST, "/api/v*/users/**").hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers(GET, "/api/v*/admins/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(POST, "/api/v*/admins/**").hasAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
