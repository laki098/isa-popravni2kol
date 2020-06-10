package app.security.model;

import app.model.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class RegistrovaniSistem extends AbstractEntity {
    private String korisnickoIme;
    private String lozinka;

    public RegistrovaniSistem(Long id, Integer version, String korisnickoIme, String lozinka) {
        super(id, version);
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public RegistrovaniSistem() {
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
}
