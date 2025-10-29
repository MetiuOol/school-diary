package pl.kurs.schooldiary.commands;

import java.time.LocalDate;

public class CreateTeacherCommand {
    private String firstName;
    private String lastName;
    private String pesel;
    private LocalDate birthDate;

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

}
