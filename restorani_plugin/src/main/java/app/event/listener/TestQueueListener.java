package app.event.listener;

import app.dto.MessageDTO;
import app.model.Porudzbina;
import app.model.Restoran;
import app.repo.RestoranRepo;
import app.security.utils.JwtUtil;
import app.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ErrorHandler;



@Component
@EnableJms
public class TestQueueListener implements ErrorHandler {
    @Autowired
    private PorudzbinaService porudzbinaService;
    @Autowired
    private RestoranRepo restoranRepo;
    @Autowired
    private JwtUtil tokenUtils;
    @Autowired
    private UserDetailsService userDetailsService;

//                                                                    ???
    @JmsListener(destination = "test-queue", containerFactory = "jmsTopicListenerContainerFactory")
    @Transactional
    public void onTestQueueEvent(MessageDTO porudzbina) {
        try {

            String username = tokenUtils.getUsername(porudzbina.getToken());
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            System.out.println(username);
            if(tokenUtils.validateToken(porudzbina.getToken(), userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);


                System.out.println("New message from test-queue!");
                System.out.print("New order: ");
                System.out.println(porudzbina.getBrojPorudzbine());


                Porudzbina newPorudzbina = new Porudzbina();
                newPorudzbina.setVersion(0);
                newPorudzbina.setBrojPorudzbine(porudzbina.getBrojPorudzbine());
                newPorudzbina.setDatumPorudzbine(porudzbina.getDatumPorudzbine());
                newPorudzbina.setStatus(porudzbina.getStatus());
                Restoran restoran = restoranRepo.findById(Long.parseLong(porudzbina.getRestoranId().toString())).orElse(null);
                if (restoran != null) {
                    newPorudzbina.setRestoran(restoran);
                } else {
                    throw new RuntimeException("Restoran nije pronadjen");
                }

                porudzbinaService.save(newPorudzbina);
            }




        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @JmsListener(destination = "test-topic")
    public void onTestTopicEvent(String message) {
        System.out.println("New message from test-topic!");
        System.out.println(message);
    }

    @Override
    public void handleError(Throwable t) {

    }
}

