/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.salud.configuracion;

import com.egg.salud.servicio.UsuarioServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    public UsuarioServicioImpl usuarioServicio;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(usuarioServicio).passwordEncoder(new BCryptPasswordEncoder());
   
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/inicio").hasAnyRole("ADMIN", "PROFESIONAL", "GUEST")
                .antMatchers("/api/guest/registrar").permitAll()
                .antMatchers("/api/profesional/registrar").permitAll()
                .antMatchers("/api/profesional/crear-oferta/**").permitAll()
                .antMatchers("/api/oferta/**").permitAll()
                .antMatchers("/api/fichero/**").permitAll()
                .antMatchers(HttpMethod.PUT , "/api/guest/{usuario}" ).hasAnyRole("ADMIN" , "GUEST")
                .antMatchers(HttpMethod.PUT , "/api/profesional/{usuario}").hasAnyRole("ADMIN", "PROFESIONAL")
                .antMatchers(HttpMethod.PUT , "/api/admin/{usuario}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE , "/api/guest/{usuario}" ).hasAnyRole("ADMIN" , "GUEST")
                .antMatchers(HttpMethod.DELETE , "/api/profesional/{usuario}" ).hasAnyRole("ADMIN" , "PROFESIONAL")
                .antMatchers(HttpMethod.DELETE , "/api/admin/{usuario}" ).hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET , "/api/guest/lista").hasAnyRole("ADMIN", "PROFESIONAL", "GUEST")
                .antMatchers(HttpMethod.GET , "/api/profesional/lista").hasAnyRole("ADMIN", "PROFESIONAL", "GUEST")
                .antMatchers(HttpMethod.GET , "/api/admin/lista").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET , "/api/admin/listaCompleta").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET , "/api/profesional/{usuario}").hasAnyRole("ADMIN", "PROFESIONAL")
                .antMatchers(HttpMethod.GET , "/api/guest/{usuario}").hasAnyRole("ADMIN","GUEST")
                .and().httpBasic()
                .and().csrf().disable();
                        
    }


}
        
