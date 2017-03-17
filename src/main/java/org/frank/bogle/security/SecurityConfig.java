package org.frank.bogle.security;

import org.frank.bogle.handler.LrqaLoginSuccessHandler;
import org.frank.bogle.handler.LrqaLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.annotation.Resource;

/**
 * Created by frankbogle on 23/06/2016.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource(name = "personAuthenticationService")
    private LrqaPersonDetailsService personDetailsService;

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
                .withUser("fcbogle").password("take2asp1r1n").roles("USER")
                .and()
                .withUser("vlad").password("take2asp1r1n").roles("USER", "ADMIN");
    }

    @Autowired
    LrqaLoginSuccessHandler personLoginSuccessHandler;

    @Autowired
    LrqaLogoutSuccessHandler personLogoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/", "/allusers", "/register", "/getalllrqaquestions", "/getalllrqapeople", "/getalllrqafeedback",
                            "/getalllrqaanswers","/bosassessorhome", "/bosfaqhome", "/lrqaappfeedback", "/lrqahome", "/lrfaqappabout").permitAll()
                    .antMatchers(HttpMethod.POST, "/register", "/lrqaappfeedback", "/post/user").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login").successHandler(personLoginSuccessHandler)
                    .permitAll()
                    .and()
                    .logout().logoutSuccessHandler(personLogoutSuccessHandler)
                    .permitAll()
                    .logoutSuccessUrl("/bosfaqhome");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(personDetailsService).passwordEncoder(passwordEncoder());
    }

}
