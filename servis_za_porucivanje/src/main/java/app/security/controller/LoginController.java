package app.security.controller;

import app.model.Korisnik;
import app.security.utils.JwtUtil;
import app.service.KorisnikService;
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
    KorisnikService service;

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
    public ResponseEntity<HashMap<String, String>> login(@RequestBody Korisnik user) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getKorisnickoIme(),
                    user.getLozinka());

            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails details = userDetailsService.loadUserByUsername(user.getKorisnickoIme());
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
    public ResponseEntity<Korisnik> register(@RequestBody Korisnik entity) {
        if (service.getByKorisnickoIme(entity.getKorisnickoIme()).isPresent()) {
            return new ResponseEntity<Korisnik>(HttpStatus.CONFLICT);
        }
        entity.setLozinka(passwordEncoder.encode(entity.getLozinka()));

        service.save(entity);

        return new ResponseEntity<Korisnik>(new Korisnik(0L, 0, entity.getKorisnickoIme(), entity.getLozinka()), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Korisnik> edit(@PathVariable("id") Long id, @RequestBody Korisnik entity) {
        Korisnik registered = service.findOne(id);
        if (registered == null) {
            return new ResponseEntity<Korisnik>(HttpStatus.NOT_FOUND);
        }
        if (service.getByKorisnickoIme(entity.getKorisnickoIme()).isPresent()) {
            return new ResponseEntity<Korisnik>(HttpStatus.CONFLICT);
        }
        entity.setLozinka(passwordEncoder.encode(entity.getLozinka()));

        registered.setLozinka(entity.getLozinka());
        service.save(registered);
        return new ResponseEntity<Korisnik>(new Korisnik(0L, 0, registered.getKorisnickoIme(), registered.getLozinka()), HttpStatus.OK);
    }

    //@Secured("ROLE_USER")
    @RequestMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<String>("Hello, user!", HttpStatus.OK);
    }

}
