package pl.kurs.schooldiary.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kurs.schooldiary.commands.CreateStudentCommand;
import pl.kurs.schooldiary.commands.UpdateStudentCommand;
import pl.kurs.schooldiary.dto.StudentDto;
import pl.kurs.schooldiary.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDto> addStudent(@RequestBody CreateStudentCommand createStudentCommand) {
        StudentDto studentDto = studentService.createStudent(createStudentCommand);
        return ResponseEntity.ok(studentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody UpdateStudentCommand updateStudentCommand, @PathVariable long id) {
        StudentDto studentDto = studentService.updateStudent(updateStudentCommand, id);
        return ResponseEntity.ok(studentDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        StudentDto studentDto = studentService.getStudentById(id);
        return ResponseEntity.ok(studentDto);
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(@PageableDefault(size = 5) Pageable pageable) {
        List<StudentDto> allStudentsDto = studentService.getAllStudents(pageable);
        return ResponseEntity.ok(allStudentsDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
