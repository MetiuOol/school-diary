package pl.kurs.schooldiary.controllers;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.kurs.schooldiary.models.person.Teacher;
import pl.kurs.schooldiary.repositories.TeacherRepository;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TeacherControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    void shouldReturnTeacherById() throws Exception {
        //given
        Teacher teacher = new Teacher();
        teacher.setFirstName("Jan");
        teacher.setLastName("Kowalski");
        teacher.setPesel("12345678901");
        teacher.setBirthDate(LocalDate.of(1980, 5, 15));

        Teacher savedTeacher = teacherRepository.save(teacher);

        //when & then
        mockMvc.perform(get("/api/v1/teachers/" + savedTeacher.getId()))  // Wysyłamy GET request
                .andExpect(status().isOk())  // Sprawdzamy czy status HTTP to 200 OK
                .andExpect(jsonPath("$.id").value(savedTeacher.getId()))  // Sprawdzamy czy ID się zgadza
                .andExpect(jsonPath("$.firstName").value("Jan"))  // Sprawdzamy imię
                .andExpect(jsonPath("$.lastName").value("Kowalski"))  // Sprawdzamy nazwisko
                .andExpect(jsonPath("$.pesel").value("12345678901"));  // Sprawdzamy PESEL
    }


    @Test
    void shouldCreateNewTeacher() throws Exception {
        //given
        String teacherJson = """
            {
                "firstName": "Anna",
                "lastName": "Nowak",
                "pesel": "98765432101",
                "birthDate": "1985-03-20"
            }
            """;

        //when & then
        mockMvc.perform(post("/api/v1/teachers")
                        .contentType("application/json")
                        .content(teacherJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("Anna"))
                .andExpect(jsonPath("$.lastName").value("Nowak"))
                .andExpect(jsonPath("$.pesel").value("98765432101"));
    }

//    @Test
//    void shouldUpdateExistingTeacher() throws Exception {
//        // given
//        Teacher teacher = new Teacher();
//        teacher.setFirstName("Jan");
//        teacher.setLastName("Kowalski");
//        teacher.setPesel("12345678901");
//        teacher.setBirthDate(LocalDate.of(1980, 5, 15));
//        Teacher savedTeacher = teacherRepository.save(teacher);
//
//        String updateJson = """
//            {
//                "firstName": "Janusz",
//                "lastName": "Kowalski-Nowak",
//                "pesel": "12345678901",
//                "birthDate": "1980-05-15"
//            }
//            """;
//
//        // when & then
//        mockMvc.perform(put("/api/v1/teachers/{id}", savedTeacher.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(updateJson))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(savedTeacher.getId()))
//                .andExpect(jsonPath("$.firstName").value("Janusz"))
//                .andExpect(jsonPath("$.lastName").value("Kowalski-Nowak"));
//    }


}
