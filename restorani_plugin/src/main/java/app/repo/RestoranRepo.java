package app.repo;

import app.model.Restoran;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestoranRepo extends PagingAndSortingRepository<Restoran, Long> {
}
