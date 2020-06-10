package app.controller;

import app.model.Korisnik;
import app.repo.KorisnikRepo;
import app.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/korisnik")
public class KorisnikController extends GenericController<Korisnik, KorisnikService, KorisnikRepo> {
    public KorisnikController() {
    }

    @Autowired
    public KorisnikController(KorisnikService service) {
        super(service);
    }
}
