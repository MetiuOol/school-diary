package pl.kurs.schooldiary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.kurs.schooldiary.models.Grade;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("SELECT g FROM Grade g WHERE g.student.id = :studentId")
    List<Grade> findGradesByStudentId(long studentId);

    @Query("SELECT g FROM Grade g WHERE g.teacher.id = :teacherId")
    List<Grade> findGradesByTeacherId(long teacherId);
}
