package pl.kurs.schooldiary.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kurs.schooldiary.models.person.Student;
import pl.kurs.schooldiary.repositories.StudentRepository;

import java.util.List;

@Service
public class StudentCrudService extends GenericCrudService<Student, StudentRepository>{

    public StudentCrudService(StudentRepository repository) {
        super(repository);
    }


    @Override
    public Page<Student> getAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }
}
