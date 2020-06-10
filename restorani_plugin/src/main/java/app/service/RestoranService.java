package app.service;

import app.model.Restoran;
import app.repo.RestoranRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestoranService extends GenericService<Restoran, RestoranRepo> {

    public RestoranService() {
    }

    @Autowired
    public RestoranService(RestoranRepo repository) {
        super(repository);
    }
}
