package app.security.controller;

import app.security.model.RegistrovaniSistem;
import app.security.service.SistemService;
import app.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    SistemService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil tokenUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @CrossOrigin
    @RequestMapping(path = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, String>> login(@RequestBody RegistrovaniSistem registrovaniSistem) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(registrovaniSistem.getKorisnickoIme(),
                    registrovaniSistem.getLozinka());

            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails details = userDetailsService.loadUserByUsername(registrovaniSistem.getKorisnickoIme());
            String userToken = tokenUtils.generateToken(details);

            HashMap<String, String> data = new HashMap<String, String>();
            data.put("token", userToken);

            return new ResponseEntity<HashMap<String, String>>(data, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<HashMap<String, String>>(HttpStatus.UNAUTHORIZED);
        }
    }

    @CrossOrigin
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<RegistrovaniSistem> register(@RequestBody RegistrovaniSistem noviSistem) {
        if (service.getByKorisnickoIme(noviSistem.getKorisnickoIme()).isPresent()) {
            return new ResponseEntity<RegistrovaniSistem>(HttpStatus.CONFLICT);
        }
        noviSistem.setLozinka(passwordEncoder.encode(noviSistem.getLozinka()));

        noviSistem.setVersion(0);
        service.save(noviSistem);

        return new ResponseEntity<RegistrovaniSistem>(new RegistrovaniSistem(0L, 0, noviSistem.getKorisnickoIme(), noviSistem.getLozinka()), HttpStatus.OK);
    }

}
