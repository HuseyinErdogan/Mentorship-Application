package jsi.mentorship.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.stereotype.Component;

import jsi.mentorship.business.abstracts.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class OpenLdapAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LdapContextSource contextSource;

    private LdapTemplate ldapTemplate;
    
    @Autowired
    private UserService userService;

    @PostConstruct
    private void initContext() {
    	
    	contextSource = new DefaultSpringSecurityContextSource(
                Collections.singletonList("ldap://localhost:8389"), "dc=springframework,dc=org");
    	
        contextSource.setAnonymousReadOnly(true);
        contextSource.setUserDn("uid={0},ou=people");
      
        contextSource.afterPropertiesSet();
        
        ldapTemplate = new LdapTemplate(contextSource);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	
        Filter filter = new EqualsFilter("uid", authentication.getName());
        Boolean authenticate = ldapTemplate.authenticate(LdapUtils.emptyLdapName(), filter.encode(),
                authentication.getCredentials().toString());
        if (authenticate) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            
            String role = this.userService.findByUsername(authentication.getName()).getData().getRole().getName();
            
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role));
            
            
            UserDetails userDetails = new User(authentication.getName() ,authentication.getCredentials().toString()
                    ,grantedAuthorities);
            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,
                    authentication.getCredentials().toString() , grantedAuthorities);
            return auth;

        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
    
    
    
}
