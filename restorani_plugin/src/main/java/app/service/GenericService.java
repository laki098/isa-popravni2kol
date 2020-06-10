package app.service;


import app.model.AbstractEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public abstract class GenericService<T extends AbstractEntity, V extends PagingAndSortingRepository<T, Long>> {


    protected V repository;

    public GenericService() {
    }

    public GenericService(V repository){
        this.repository = repository;
    }

    public Iterable<T> findAll() {
        return repository.findAll();
    }

    public T findOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    public T save(T entity) {

        return repository.save(entity);

    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }


}
