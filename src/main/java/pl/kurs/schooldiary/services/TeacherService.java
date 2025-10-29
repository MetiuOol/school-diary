package pl.kurs.schooldiary.services;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import pl.kurs.schooldiary.commands.CreateTeacherCommand;
import pl.kurs.schooldiary.commands.UpdateTeacherCommand;
import pl.kurs.schooldiary.dto.TeacherDto;
import pl.kurs.schooldiary.models.person.Teacher;

import java.util.List;

@Service
@Transactional
public class TeacherService {
    private final TeacherCrudService teacherCrudService;
    private final ModelMapper mapper;


    public TeacherService(TeacherCrudService teacherCrudService, ModelMapper mapper) {
        this.teacherCrudService = teacherCrudService;
        this.mapper = mapper;
    }

    public TeacherDto createTeacher(CreateTeacherCommand createTeacherCommand){
        Teacher teacher = mapper.map(createTeacherCommand, Teacher.class);
        Teacher persistedTeacher = teacherCrudService.add(teacher);
        TeacherDto teacherDto = mapper.map(persistedTeacher, TeacherDto.class);
        return teacherDto;

    }

    public TeacherDto updateTeacher(UpdateTeacherCommand updateTeacherCommand, long id){
        Teacher teacherForUpdate =  mapper.map(updateTeacherCommand, Teacher.class);
        Teacher updatedTeacher = teacherCrudService.edit(teacherForUpdate, id);
        return mapper.map(updatedTeacher, TeacherDto.class);

    }

    public TeacherDto getTeacherById(Long id){
        Teacher teacher = teacherCrudService.get(id);
        return mapper.map(teacher, TeacherDto.class);

    }

    public List<TeacherDto> getAllTeachers(Pageable pageable){
        Page<Teacher> teacherList = teacherCrudService.getAll(pageable);
        List<TeacherDto> teacherDtos = teacherList.stream()
                .map(t -> mapper.map(t, TeacherDto.class))
                .toList();
        return teacherDtos;

    }


    public void deleteTeacher(long id){
        teacherCrudService.delete(id);
    }


    //testy integracyjne restapi springboot
    //TODO DELETE teacher id = 2 nie dziala, utworzyc na nowo tabele i zobaczyc co sie dzieje z delete
}
