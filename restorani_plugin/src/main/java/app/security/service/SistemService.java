package app.security.service;


import app.repo.SistemRepo;
import app.security.model.RegistrovaniSistem;
import app.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SistemService extends GenericService<RegistrovaniSistem, SistemRepo> {

    public SistemService() {
    }

    @Autowired
    public SistemService(SistemRepo repository) {
        super(repository);
    }

    public Optional<RegistrovaniSistem> getByKorisnickoIme(String korisnickoIme) {
        return repository.getByKorisnickoIme(korisnickoIme);
    }
}