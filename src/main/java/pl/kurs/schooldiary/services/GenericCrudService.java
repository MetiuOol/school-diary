package pl.kurs.schooldiary.services;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.schooldiary.exceptions.IdNotMatchException;
import pl.kurs.schooldiary.exceptions.IllegalEntityStateException;
import pl.kurs.schooldiary.exceptions.ProvidedNullIdException;
import pl.kurs.schooldiary.exceptions.RequestedEntityNotFoundException;
import pl.kurs.schooldiary.models.Identificationable;


import java.lang.reflect.ParameterizedType;
import java.util.List;

@Transactional
public abstract class GenericCrudService<T extends Identificationable, R extends JpaRepository<T, Long>> implements ICrudService<T> {

    protected R repository;
    protected Class<T> entityType;

    public GenericCrudService(R repository) {
        this.repository = repository;
        this.entityType = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    public R getRepository() {
        return repository;
    }

    public Class<T> getEntityType() {
        return entityType;
    }

    @Override
    public T add(T entity) {
        if (entity.getId() != null) {
            throw new IllegalEntityStateException("Entity ID should be null before persisting!", entityType);
        }
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new ProvidedNullIdException("ID shouldn't be null!", entityType);
        }
        if (!repository.existsById(id)){
            throw new RequestedEntityNotFoundException("Entity for delete with id " + id + " not found!", entityType);
        }
        repository.deleteById(id);

    }

    @Override
    public T edit(T entity, Long id) {
        if (entity.getId() == null) {
            throw new IllegalEntityStateException("Entity ID shouldn't be null before updating!", entityType);
        }
//        if (id == null) {
//            throw new ProvidedNullIdException("ID shouldn't be null!", entityType);  //DEAD CODE
//        }
        if (!entity.getId().equals(id)) {
            throw new IdNotMatchException("ID from Path variable do not match body ID!", entityType);
        }
        if (!repository.existsById(entity.getId())) {
            throw new RequestedEntityNotFoundException("Entity for update with ID " + entity.getId() + " not found!", entityType);
        }
        return repository.save(entity);
    }

    @Override
    public T get(Long id) {
        if (id == null) {
            throw new ProvidedNullIdException("ID shouldn't be null!", entityType);
        }

        return repository.findById(id).orElseThrow(() -> new RequestedEntityNotFoundException("Entity with id " + id + " not found:  " + entityType.getSimpleName(), entityType));
    }

     @Override
    public Page<T> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
