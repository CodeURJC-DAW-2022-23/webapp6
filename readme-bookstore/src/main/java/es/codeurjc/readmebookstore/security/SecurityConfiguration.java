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
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
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
        http.authorizeRequests().antMatchers("/user-page").hasAnyRole("USER", "ADMIN");
        http.authorizeRequests().antMatchers("/checkout-page/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/statistics-page/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/update-offer/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/update-review/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/update-user/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/upload-offer-page/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/upload-review-page/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN");
        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/user-page");
        http.formLogin().failureUrl("/loginerror-page");
        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");
    }
}