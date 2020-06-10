package app.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MessageDTO implements Serializable {
    private static final long serialVersionUID = -5008238127430475320L;
    private String brojPorudzbine;
    private LocalDateTime datumPorudzbine;
    private Long restoranId;
    private String status;

    private String token;

    public MessageDTO() {
    }

    public MessageDTO(String brojPorudzbine, LocalDateTime datumPorudzbine, Long restoranId, String status, String token) {
        this.brojPorudzbine = brojPorudzbine;
        this.datumPorudzbine = datumPorudzbine;
        this.restoranId = restoranId;
        this.status = status;
        this.token = token;
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

    public Long getRestoranId() {
        return restoranId;
    }

    public void setRestoranId(Long restoranId) {
        this.restoranId = restoranId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
