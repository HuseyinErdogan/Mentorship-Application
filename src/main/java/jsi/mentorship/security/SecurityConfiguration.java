package jsi.mentorship.security;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .authorizeRequests()
	           //         .antMatchers("/api/mentorship/**").hasRole("MANAGER")
	           //         .antMatchers("/admin").hasRole("MANAGER")
	           //         .antMatchers("/user").hasRole("USER")
	                    .anyRequest().fullyAuthenticated()
	                .and()
	                    .formLogin();
	    }

	    @Override
	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	                .ldapAuthentication()
	                    .userDnPatterns("uid={0},ou=people")
	                    .userSearchBase("ou=people")
	                    .userSearchFilter("uid={0}")
	                    .groupSearchBase("ou=groups")
	                    .groupSearchFilter("uniqueMember={0}")
	                .contextSource(contextSource())
	                .passwordCompare()
	         //           .passwordEncoder(new LdapShaPasswordEncoder())
	                    .passwordAttribute("userPassword");
	    }

	    @Bean
	    public DefaultSpringSecurityContextSource contextSource() {
	        return  new DefaultSpringSecurityContextSource(
	                Collections.singletonList("ldap://localhost:8389"), "dc=springframework,dc=org");
	    }
}
