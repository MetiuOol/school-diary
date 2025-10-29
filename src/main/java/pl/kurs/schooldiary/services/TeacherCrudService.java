package pl.kurs.schooldiary.services;

import org.springframework.stereotype.Service;
import pl.kurs.schooldiary.models.person.Teacher;
import pl.kurs.schooldiary.repositories.TeacherRepository;

@Service
public class TeacherCrudService extends GenericCrudService<Teacher, TeacherRepository>{

    public TeacherCrudService(TeacherRepository repository) {
        super(repository);
    }
}
