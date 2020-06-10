package app.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Porudzbina extends AbstractEntity {
    private String brojPorudzbine;
    private LocalDateTime datumPorudzbine;
    private String status;

    @ManyToOne
    private Restoran restoran;

    public Porudzbina() {
    }

    public Porudzbina(Long id, Integer version, String brojPorudzbine, LocalDateTime datumPorudzbine, String status, Restoran restoran) {
        super(id, version);
        this.brojPorudzbine = brojPorudzbine;
        this.datumPorudzbine = datumPorudzbine;
        this.status = status;
        this.restoran = restoran;
    }

    public String getBrojPorudzbine() {
        return brojPorudzbine;
    }

    public void setBrojPorudzbine(String brojPorudzbine) {
        this.brojPorudzbine = brojPorudzbine;
    }

    public LocalDateTime getDatumPorudzbine() {
        return datumPorudzbine;
    }

    public void setDatumPorudzbine(LocalDateTime datumPorudzbine) {
        this.datumPorudzbine = datumPorudzbine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }
}
