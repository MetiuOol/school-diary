package pl.kurs.schooldiary.services;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICrudService<T> {
    T add(T entity);
    void delete(Long id);
    T edit(T entity, Long id);
    T get(Long id);
    Page<T> getAll(Pageable pageable);
}
