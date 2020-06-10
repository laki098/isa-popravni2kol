package app.service;

import app.model.Porudzbina;
import app.repo.PorudzbinaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PorudzbinaService extends GenericService<Porudzbina, PorudzbinaRepo> {
    @Autowired
    private JmsTemplate jmsTemplate;

    public PorudzbinaService() {
    }

    @Autowired
    public PorudzbinaService(PorudzbinaRepo repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Porudzbina save(Porudzbina entity) {
        return super.save(entity);
    }
}
