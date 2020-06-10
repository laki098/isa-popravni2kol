package app.security.service;

import app.model.Korisnik;
import app.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    KorisnikService service;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String korisnickoIme) throws UsernameNotFoundException {
        Optional<Korisnik> registerd = service.getByKorisnickoIme(korisnickoIme);

        if(registerd.isPresent()) {
            ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

            return new org.springframework.security.core.userdetails.User(registerd.get().getKorisnickoIme(), registerd.get().getLozinka(), grantedAuthorities);
        }

        return null;
    }
}
