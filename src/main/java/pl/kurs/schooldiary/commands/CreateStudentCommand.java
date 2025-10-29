package pl.kurs.schooldiary.commands;

import java.time.LocalDate;

public class CreateStudentCommand {
    private String firstName;
    private String lastName;
    private String pesel;
    private LocalDate birthDate;
    private Long teacherId;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Long getTeacherId() {
        return teacherId;
    }
}
