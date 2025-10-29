package pl.kurs.schooldiary.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import pl.kurs.schooldiary.models.person.Student;
import pl.kurs.schooldiary.models.person.Teacher;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "grades")
public class Grade implements Serializable, Identificationable {

    private static final long serialVersionUID = 1L;

    @Version
    private Integer version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grade")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GradeLevel gradeLevel;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = false)
    private String schoolSubject;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    public Grade() {
    }

    public Grade(GradeLevel gradeLevel, LocalDate date, Student student, String schoolSubject, String description, Teacher teacher) {
        this.gradeLevel = gradeLevel;
        this.date = date;
        this.student = student;
        this.schoolSubject = schoolSubject;
        this.description = description;
        this.teacher = teacher;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GradeLevel getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(GradeLevel gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getSchoolSubject() {
        return schoolSubject;
    }

    public void setSchoolSubject(String schoolSubject) {
        this.schoolSubject = schoolSubject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Objects.equals(id, grade.id) && gradeLevel == grade.gradeLevel && Objects.equals(date, grade.date) && Objects.equals(student, grade.student) && Objects.equals(schoolSubject, grade.schoolSubject) && Objects.equals(description, grade.description) && Objects.equals(teacher, grade.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gradeLevel, date, student, schoolSubject, description, teacher);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", gradeLevel=" + gradeLevel +
                ", date=" + date +
                ", student=" + student +
                ", schoolSubject='" + schoolSubject + '\'' +
                ", description='" + description + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
