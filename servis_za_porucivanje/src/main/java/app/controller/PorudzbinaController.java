package app.controller;

import app.model.Porudzbina;
import app.repo.PorudzbinaRepo;
import app.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
import javax.jms.Topic;

@Controller
@RequestMapping("/api/porudzbina")
public class PorudzbinaController extends GenericController<Porudzbina, PorudzbinaService, PorudzbinaRepo> {

    @Autowired
    private Topic topic;
    @Autowired
    private Queue queue;
    @Autowired
    private JmsTemplate jmsTemplate;


    public PorudzbinaController() {
    }

    @Autowired
    public PorudzbinaController(PorudzbinaService service) {
        super(service);
    }


    @RequestMapping(path = "/korisnik/{korisnickoIme}", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity<Iterable<Porudzbina>> getOne(@PathVariable("korisnickoIme") String korisnickoIme) {
        if (getUserFromToken().equals(korisnickoIme)) {
            Iterable<Porudzbina> ent = service.findAllPorudzbinaWhereKorisnik(korisnickoIme);

            if (ent != null) {
                System.out.println(ent);
                return new ResponseEntity<Iterable<Porudzbina>>(ent, HttpStatus.OK);
            }
            return new ResponseEntity<Iterable<Porudzbina>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Iterable<Porudzbina>>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("message/{message}")
    public ResponseEntity<String> publish(@PathVariable("message") final String message){
        jmsTemplate.convertAndSend(topic, message);
        jmsTemplate.convertAndSend("test-queue", message);
        System.out.println("Message sent");
        return new ResponseEntity(message, HttpStatus.OK);
    }

    private String getUserFromToken() {
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (loggedUser instanceof UserDetails) {
            String username = ((UserDetails)loggedUser).getUsername();
            return username;
        } else {
            String username = loggedUser.toString();
            return username;
        }
    }


}
