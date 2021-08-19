package jsi.mentorship.security.config;

import jsi.mentorship.security.JwtAuthenticationEntryPoint;
import jsi.mentorship.security.filter.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String ROLE_ADMIN="ADMIN";
	private static final String ROLE_MENTOR="MENTOR";
	private static final String ROLE_MENTEE="MENTEE";

    private OpenLdapAuthenticationProvider openLdapAuthenticationProvider;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtRequestFilter jwtRequestFilter;

    public WebSecurityConfig(OpenLdapAuthenticationProvider openLdapAuthenticationProvider,
                             JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                             JwtRequestFilter jwtRequestFilter) {
        this.openLdapAuthenticationProvider = openLdapAuthenticationProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtRequestFilter = jwtRequestFilter;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth  	
        	.authenticationProvider(openLdapAuthenticationProvider);		
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity
        		.httpBasic().and()
                .cors().and().csrf().disable()
                .headers()
                .frameOptions()
                .deny()
                .and()
                // dont authenticate this particular request
                .authorizeRequests().antMatchers("/api/login").permitAll()
                					.antMatchers("/api/users/getByGoogleAuth").permitAll()
                					//.antMatchers("/api/users/**").hasRole(ROLE_ADMIN)
                					
                					.antMatchers("/api/subjects").hasAnyRole(ROLE_ADMIN,ROLE_MENTOR,ROLE_MENTEE)
                					.antMatchers("/api/subjects/**").hasRole(ROLE_ADMIN)
                					
                					.antMatchers("/api/appeals/**").hasAnyRole(ROLE_ADMIN,ROLE_MENTOR,ROLE_MENTEE)
                					
                					.antMatchers("/api/appeals/becomeMentor/makeMenteeMentor").hasRole(ROLE_ADMIN)
                					.antMatchers("/api/appeals/mentorshipAppeals/acceptAppeal").hasRole(ROLE_MENTOR)
                					.antMatchers("/api/mentorships/**").hasAnyRole(ROLE_ADMIN,ROLE_MENTOR,ROLE_MENTEE)
                					
                        .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
    }
}
