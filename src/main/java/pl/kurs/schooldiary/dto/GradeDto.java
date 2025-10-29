package pl.kurs.schooldiary.dto;


import pl.kurs.schooldiary.models.GradeLevel;

import java.time.LocalDate;

public class GradeDto {
    private Long id;
    private GradeLevel gradeLevel;
    private LocalDate date;
    private StudentSimpleDto student;
    private String schoolSubject;
    private String description;
    private TeacherSimpleDto teacher;

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

    public StudentSimpleDto getStudent() {
        return student;
    }

    public void setStudent(StudentSimpleDto student) {
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

    public TeacherSimpleDto getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherSimpleDto teacher) {
        this.teacher = teacher;
    }


}
