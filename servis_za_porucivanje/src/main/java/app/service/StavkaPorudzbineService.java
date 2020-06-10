package app.service;

import app.model.StavkaPorudzbine;
import app.repo.StavkaPorudzbineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StavkaPorudzbineService extends GenericService<StavkaPorudzbine, StavkaPorudzbineRepo> {
    public StavkaPorudzbineService() {
    }

    @Autowired
    public StavkaPorudzbineService(StavkaPorudzbineRepo repository) {
        super(repository);
    }
}
