package pl.kurs.schooldiary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.schooldiary.models.person.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
