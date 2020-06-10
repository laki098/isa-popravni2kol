package app.repo;

import app.model.StavkaPorudzbine;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StavkaPorudzbineRepo extends PagingAndSortingRepository<StavkaPorudzbine, Long> {
}
