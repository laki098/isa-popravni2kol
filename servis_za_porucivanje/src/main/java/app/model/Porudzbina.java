package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Porudzbina extends AbstractEntity {
    private String brojPorudzbine;
    private Long restoran;
    private LocalDateTime datumPorudzbine;
    private String status;

    @ManyToOne
    private Korisnik korisnik;

    @JsonIgnore
    @OneToMany(mappedBy = "porudzbina")
    private List<StavkaPorudzbine> stavkePorudzbine;

    public Porudzbina() {
    }

    public Porudzbina(Long id, Integer version, String brojPorudzbine, Long restoran, LocalDateTime datumPorudzbine, String status, Korisnik korisnik) {
        super(id, version);
        this.brojPorudzbine = brojPorudzbine;
        this.restoran = restoran;
        this.datumPorudzbine = datumPorudzbine;
        this.status = status;
        this.korisnik = korisnik;
    }

    public String getBrojPorudzbine() {
        return brojPorudzbine;
    }

    public void setBrojPorudzbine(String brojPorudzbine) {
        this.brojPorudzbine = brojPorudzbine;
    }

    public Long getRestoran() {
        return restoran;
    }

    public void setRestoran(Long restoran) {
        this.restoran = restoran;
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

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public List<StavkaPorudzbine> getStavkePorudzbine() {
        return stavkePorudzbine;
    }

    public void setStavkePorudzbine(List<StavkaPorudzbine> stavkePorudzbine) {
        this.stavkePorudzbine = stavkePorudzbine;
    }
}
