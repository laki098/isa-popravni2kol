package app.service;

import app.model.Korisnik;
import app.repo.KorisnikRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KorisnikService extends GenericService<Korisnik, KorisnikRepo> {

    public KorisnikService() {
    }

    @Autowired
    public KorisnikService(KorisnikRepo repository) {
        super(repository);
    }

    public Optional<Korisnik> getByKorisnickoIme(String korisnickoIme) {
        return repository.getByKorisnickoIme(korisnickoIme);
    }
}
