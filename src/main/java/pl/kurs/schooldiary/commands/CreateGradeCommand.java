package pl.kurs.schooldiary.commands;

import pl.kurs.schooldiary.models.GradeLevel;

import java.time.LocalDate;

public class CreateGradeCommand {
    private GradeLevel gradeLevel;
    private LocalDate date;
    private Long studentId;
    private String schoolSubject;
    private String description;
    private Long teacherId;

    public GradeLevel getGradeLevel() {
        return gradeLevel;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getSchoolSubject() {
        return schoolSubject;
    }

    public String getDescription() {
        return description;
    }

    public Long getTeacherId() {
        return teacherId;
    }
}
