package app.repo;

import app.model.Porudzbina;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PorudzbinaRepo extends PagingAndSortingRepository<Porudzbina, Long> {
    @Query("SELECT r FROM Porudzbina r, Korisnik k where r.korisnik = k and k.korisnickoIme = ?1")
    Iterable<Porudzbina> findAllPorudzbinaWhereKorisnik(String korisnickoIme);
}