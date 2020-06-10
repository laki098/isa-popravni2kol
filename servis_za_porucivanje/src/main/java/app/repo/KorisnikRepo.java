package app.repo;

import app.model.Korisnik;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KorisnikRepo extends PagingAndSortingRepository<Korisnik, Long> {
    Optional<Korisnik> getByKorisnickoIme(String korisnickoIme);
}
