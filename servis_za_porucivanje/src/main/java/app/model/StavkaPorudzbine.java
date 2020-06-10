package app.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class StavkaPorudzbine extends AbstractEntity {
    private String naziv;
    private Double cena;

    @ManyToOne
    private Porudzbina porudzbina;

    public StavkaPorudzbine() {
    }

    public StavkaPorudzbine(Long id, Integer version, String naziv, Double cena, Porudzbina porudzbina) {
        super(id, version);
        this.naziv = naziv;
        this.cena = cena;
        this.porudzbina = porudzbina;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }
}
