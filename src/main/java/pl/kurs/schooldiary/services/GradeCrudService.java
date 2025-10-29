package pl.kurs.schooldiary.services;

import org.springframework.stereotype.Service;
import pl.kurs.schooldiary.models.Grade;
import pl.kurs.schooldiary.repositories.GradeRepository;

import java.util.List;

@Service
public class GradeCrudService extends GenericCrudService<Grade, GradeRepository> {

    public GradeCrudService(GradeRepository repository) {
        super(repository);
    }

    List<Grade> getAllGradesByStudentId(long studentId){
        return repository.findGradesByStudentId(studentId);
    }
    List<Grade> getAllGradesByTeacherId(long teacherId){
        return repository.findGradesByTeacherId(teacherId);
    }
    
}
