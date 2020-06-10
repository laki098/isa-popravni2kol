package app.controller;

import app.model.StavkaPorudzbine;
import app.repo.StavkaPorudzbineRepo;
import app.service.StavkaPorudzbineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/stavkaPorudzbine")
public class StavkaPorudzbineController extends GenericController<StavkaPorudzbine, StavkaPorudzbineService, StavkaPorudzbineRepo> {
    public StavkaPorudzbineController() {
    }

    @Autowired
    public StavkaPorudzbineController(StavkaPorudzbineService service) {
        super(service);
    }
}
