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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kurs.schooldiary.commands.CreateGradeCommand;
import pl.kurs.schooldiary.commands.UpdateGradeCommand;
import pl.kurs.schooldiary.dto.GradeDto;
import pl.kurs.schooldiary.services.GradeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/grades")
public class GradeController {
    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping
    public ResponseEntity<GradeDto> addGrade(@RequestBody CreateGradeCommand createGradeCommand) {
        GradeDto gradeDto = gradeService.createGrade(createGradeCommand);
        return ResponseEntity.ok(gradeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradeDto> updateGrade(@RequestBody UpdateGradeCommand updateGradeCommand, @PathVariable long id) {
        GradeDto gradeDto = gradeService.updateGrade(updateGradeCommand, id);
        return ResponseEntity.ok(gradeDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeDto> getGradeById(@PathVariable long id) {
        GradeDto gradeDto = gradeService.getGradeById(id);
        return ResponseEntity.ok(gradeDto);
    }

    @GetMapping
    public ResponseEntity<List<GradeDto>> getAllGrades(@PageableDefault(size = 5) Pageable pageable) {
        List<GradeDto> allGradesDto = gradeService.getAllGrades(pageable);
        return ResponseEntity.ok(allGradesDto);
    }

    @GetMapping("/byStudent")
    public ResponseEntity<List<GradeDto>> getAllGradesByStudentId(@RequestParam("id") long studentId) {
        List<GradeDto> allGradesDto = gradeService.getAllGradesByStudentId(studentId);
        return ResponseEntity.ok(allGradesDto);
    }

    @GetMapping("/byTeacher")
    public ResponseEntity<List<GradeDto>> getAllGradesByTeacherId(@RequestParam("id") long teacherId) {
        List<GradeDto> allGradesDto = gradeService.getAllGradesByTeacherId(teacherId);
        return ResponseEntity.ok(allGradesDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable long id){
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }
}
