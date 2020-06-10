package app.controller;

import app.model.Porudzbina;
import app.repo.PorudzbinaRepo;
import app.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/porudzbina")
public class PorudzbinaController extends GenericController<Porudzbina, PorudzbinaService, PorudzbinaRepo> {

    public PorudzbinaController() {
    }

    @Autowired
    public PorudzbinaController(PorudzbinaService service) {
        super(service);
    }

}
