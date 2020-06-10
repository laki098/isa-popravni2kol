package app.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restoran extends AbstractEntity {
    private String naziv;

    @OneToMany(mappedBy = "restoran")
    private List<Porudzbina> porudzbine = new ArrayList<>();

    public Restoran() {
    }

    public Restoran(Long id, Integer version, String naziv) {
        super(id, version);
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(List<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }
}
