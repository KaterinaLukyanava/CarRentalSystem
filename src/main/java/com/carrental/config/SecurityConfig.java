package com.carrental.config;


import com.carrental.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ClientService clientService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SecurityConfig(ClientService clientService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.clientService = clientService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests()
                .antMatchers("/cars","/myOrder","/order").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")//данная страница
                .antMatchers("/admin","/clientEdit","/addCar").hasAuthority("ROLE_ADMIN")
                .and()// иначе
                .formLogin()//перенаправляем на страницу логин
                .and()
                .authorizeRequests()
                .antMatchers("/", "/**")
                .permitAll()//страницы выше доступны всем
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("user")
                .passwordParameter("pass")
                .defaultSuccessUrl("/home")//указываем на какую страницу будет переходить после регистрации
                .and()
                .logout().logoutSuccessUrl("/home");




    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(clientService).passwordEncoder(bCryptPasswordEncoder);

    }
}



