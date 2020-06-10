package app.service;

import app.model.Porudzbina;
import app.repo.PorudzbinaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.dto.MessageDTO;

@Service
public class PorudzbinaService extends GenericService<Porudzbina, PorudzbinaRepo> {

    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzaXN0ZW0xIiwiY3JlYXRlZCI6MTU5MDc2NDk0MDY2OSwiZXhwIjoxNTkzMzU2OTQwfQ.SoN5F6kGhtrbfQRz5JZqLymBulmZcT2J1NiMuDAmyBsLwPmMuwZhjTxAwjGNxBpM2MRy7OdqSH4hTdaduWdJMQ";

    @Autowired
    private JmsTemplate jmsTemplate;

    public PorudzbinaService() {
    }

    @Autowired
    public PorudzbinaService(PorudzbinaRepo repository) {
        super(repository);
    }

    public Iterable<Porudzbina> findAllPorudzbinaWhereKorisnik(String korisnickoIme) {
        return repository.findAllPorudzbinaWhereKorisnik(korisnickoIme);
    }

    @Override
    @Transactional
    public Porudzbina save(Porudzbina entity) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setBrojPorudzbine(entity.getBrojPorudzbine());
        messageDTO.setDatumPorudzbine(entity.getDatumPorudzbine());
        messageDTO.setRestoranId(entity.getRestoran());
        messageDTO.setStatus(entity.getStatus());

        messageDTO.setToken(token);
        jmsTemplate.convertAndSend("test-queue", messageDTO);
        System.out.println("Message sent to test-queue");
        return super.save(entity);
    }
}
