package app.repo;

import app.model.Porudzbina;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PorudzbinaRepo extends PagingAndSortingRepository<Porudzbina, Long> {
}
