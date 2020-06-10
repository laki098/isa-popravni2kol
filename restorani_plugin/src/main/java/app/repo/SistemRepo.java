package app.repo;

import app.security.model.RegistrovaniSistem;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SistemRepo extends PagingAndSortingRepository<RegistrovaniSistem, Long> {
    Optional<RegistrovaniSistem> getByKorisnickoIme(String korisnickoIme);
}
