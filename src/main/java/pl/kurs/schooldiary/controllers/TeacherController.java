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
import pl.kurs.schooldiary.commands.CreateTeacherCommand;
import pl.kurs.schooldiary.commands.UpdateTeacherCommand;
import pl.kurs.schooldiary.dto.TeacherDto;
import pl.kurs.schooldiary.services.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<TeacherDto> addTeacher(@RequestBody CreateTeacherCommand createTeacherCommand) {
        TeacherDto teacherDto = teacherService.createTeacher(createTeacherCommand);
        return ResponseEntity.ok(teacherDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@RequestBody UpdateTeacherCommand updateTeacherCommand, @PathVariable long id){
        TeacherDto teacherDto = teacherService.updateTeacher(updateTeacherCommand, id);
        return ResponseEntity.ok(teacherDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long id){
        TeacherDto teacherDto = teacherService.getTeacherById(id);
        return ResponseEntity.ok(teacherDto);
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers(@PageableDefault(size = 5) Pageable pageable) {
        List<TeacherDto> allTeachersDto = teacherService.getAllTeachers(pageable);
        return ResponseEntity.ok(allTeachersDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable long id){
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }



}
