package jsi.mentorship.api.controllers;

import jsi.mentorship.models.concretes.User;
import jsi.mentorship.security.JwtTokenUtil;
import jsi.mentorship.business.abstracts.UserService;
import jsi.mentorship.ws.request.JwtRequest;
import jsi.mentorship.ws.request.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;



    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception
    {
        
        final User userDetails = userService.findByUsername(authenticationRequest.getUsername()).getData();
        
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final String token = jwtTokenUtil.generateToken(userDetails);
        
        return ResponseEntity.ok(new JwtResponse(token, userDetails));
    }
    

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
