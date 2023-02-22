package es.codeurjc.readmebookstore.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.annotation.security.PermitAll;
import org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    @Autowired
	RepositoryUserDetailsService userDetailsService;

    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}

    
    

    /* @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	// Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        // Private pages */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((autorizeRequests ) -> autorizeRequests 
        .requestMatchers("/admin-page").hasRole("ADMIN")   
        .requestMatchers( "/**").permitAll() 
        //.formLogin(withDefaults())   
        );
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/user-page");
        http.formLogin().failureUrl("/loginerror-page");

        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");

       
        //http.formLogin().loginPage("/login-page").loginProcessingUrl("/login");
        //http.authorizeHttpRequests().requestMatchers("/index").permitAll().anyRequest().authenticated();
        
    return http.build();
}


} 




/* 
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	RepositoryUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	// Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        // Private pages
        http.authorizeRequests().antMatchers("/newbook").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/editbook/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/removebook/*").hasAnyRole("ADMIN");
        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/");
        http.formLogin().failureUrl("/loginerror");
        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");
    }
}*/