package app.security.model;

public class AuthRequest {
    private String maticniBroj;
    private String loznika;

    public AuthRequest(String maticniBroj, String loznika) {
        this.maticniBroj = maticniBroj;
        this.loznika = loznika;
    }

    public AuthRequest() {
    }

    public String getMaticniBroj() {
        return maticniBroj;
    }

    public void setMaticniBroj(String maticniBroj) {
        this.maticniBroj = maticniBroj;
    }

    public String getLoznika() {
        return loznika;
    }

    public void setLoznika(String loznika) {
        this.loznika = loznika;
    }
}
