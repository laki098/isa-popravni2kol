package app.controller;


import app.model.AbstractEntity;
import app.service.GenericService;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public abstract class GenericController<T extends AbstractEntity, V extends GenericService<T, R>, R extends PagingAndSortingRepository<T, Long>> {


    protected V service;

    public GenericController() {
    }

    public GenericController(V service) {
        this.service = service;

    }

    @RequestMapping(path ="", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity<Iterable<T>> getAll() {
        return new ResponseEntity<Iterable<T>>(service.findAll(), HttpStatus.OK);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity<T> getOne(@PathVariable("id") Long id) {
        T ent = service.findOne(id);

        if (ent != null) {
            System.out.println(ent);
            return new ResponseEntity<T>(ent, HttpStatus.OK);
        }
        return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(path = "", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<T> create(@RequestBody T entity) {
        if (service.findOne(entity.getId()) != null) {
            return new ResponseEntity<T>(HttpStatus.CONFLICT);
        }
        T savedEntity = service.save(entity);
        return new ResponseEntity<T>(savedEntity, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @CrossOrigin
    public ResponseEntity<T> update(@PathVariable("id") Long id,
                                             @RequestBody T entity) {
        T ent = service.findOne(id);
        if (ent == null) {
            return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
        }
        service.save(entity);
        return new ResponseEntity<T>(entity, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @CrossOrigin
    public ResponseEntity<?> deleteRacun(@PathVariable("id") Long id) {
        if (service.findOne(id) == null) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }


}
