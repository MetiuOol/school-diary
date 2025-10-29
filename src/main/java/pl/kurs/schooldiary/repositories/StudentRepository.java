package pl.kurs.schooldiary.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.kurs.schooldiary.models.person.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.teacher")
    List<Student> findAll();

    @EntityGraph(attributePaths = "teacher")
    @Query("SELECT s FROM Student s")
    Page<Student> findAll(Pageable pageable);

}
