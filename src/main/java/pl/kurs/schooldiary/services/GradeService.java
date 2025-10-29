package pl.kurs.schooldiary.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kurs.schooldiary.commands.CreateGradeCommand;
import pl.kurs.schooldiary.commands.UpdateGradeCommand;
import pl.kurs.schooldiary.dto.GradeDto;
import pl.kurs.schooldiary.models.Grade;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GradeService {

    private final GradeCrudService gradeCrudService;
    private final StudentCrudService studentCrudService;
    private final TeacherCrudService teacherCrudService;
    private final ModelMapper mapper;


    public GradeService(GradeCrudService gradeCrudService, StudentCrudService studentCrudService, TeacherCrudService teacherCrudService, ModelMapper mapper) {
        this.gradeCrudService = gradeCrudService;
        this.studentCrudService = studentCrudService;
        this.teacherCrudService = teacherCrudService;
        this.mapper = mapper;
    }

    public GradeDto createGrade(CreateGradeCommand createGradeCommand) {
        Grade grade = mapper.map(createGradeCommand, Grade.class);
        grade.setStudent(studentCrudService.get(createGradeCommand.getStudentId()));
        grade.setTeacher(teacherCrudService.get(createGradeCommand.getTeacherId()));
        Grade persistedGrade = gradeCrudService.add(grade);
        GradeDto gradeDto = mapper.map(persistedGrade, GradeDto.class);
        return gradeDto;

    }

    public GradeDto updateGrade(UpdateGradeCommand updateGradeCommand, long id) {
        Grade gradeForUpdate = mapper.map(updateGradeCommand, Grade.class);
        gradeForUpdate.setStudent(studentCrudService.get(updateGradeCommand.getStudentId()));
        gradeForUpdate.setTeacher(teacherCrudService.get(updateGradeCommand.getTeacherId()));
        Grade updatedGrade = gradeCrudService.edit(gradeForUpdate, id);
        return mapper.map(updatedGrade, GradeDto.class);

    }

    public GradeDto getGradeById(Long id) {
        Grade grade = gradeCrudService.get(id);
        return mapper.map(grade, GradeDto.class);

    }

    public List<GradeDto> getAllGrades(Pageable pageable) {
        Page<Grade> gradeList = gradeCrudService.getAll(pageable);
        List<GradeDto> gradeDtos = gradeList.stream()
                .map(g -> mapper.map(g, GradeDto.class))
                .toList();
        return gradeDtos;
    }

    public List<GradeDto> getAllGradesByStudentId(long studentId){
        List<Grade> allGradesByStudentId = gradeCrudService.getAllGradesByStudentId(studentId);
        List<GradeDto> gradeDtos = allGradesByStudentId.stream()
                .map(g -> mapper.map(g, GradeDto.class))
                .toList();
        return gradeDtos;
    }

    public List<GradeDto> getAllGradesByTeacherId(long teacherId){
        List<Grade> allGradesByTeacherId = gradeCrudService.getAllGradesByTeacherId(teacherId);
        List<GradeDto> gradeDtos = allGradesByTeacherId.stream()
                .map(g -> mapper.map(g, GradeDto.class))
                .toList();
        return gradeDtos;
    }

    //TODO getAllGradesByTeacherId() 




    public void deleteGrade(long id) {
        gradeCrudService.delete(id);
    }
}




