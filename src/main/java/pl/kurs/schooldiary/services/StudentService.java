package pl.kurs.schooldiary.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kurs.schooldiary.commands.CreateStudentCommand;
import pl.kurs.schooldiary.commands.UpdateStudentCommand;
import pl.kurs.schooldiary.dto.StudentDto;
import pl.kurs.schooldiary.models.person.Student;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {

    private final StudentCrudService studentCrudService;
    private final TeacherCrudService teacherCrudService;
    private final ModelMapper mapper;


    public StudentService(StudentCrudService studentCrudService, TeacherCrudService teacherCrudService, ModelMapper mapper) {
        this.studentCrudService = studentCrudService;
        this.teacherCrudService = teacherCrudService;
        this.mapper = mapper;
    }


    public StudentDto createStudent(CreateStudentCommand createStudentCommand){
        Student student = mapper.map(createStudentCommand, Student.class);
        student.setTeacher(teacherCrudService.get(createStudentCommand.getTeacherId()));


        Student persistedStudent = studentCrudService.add(student);
        StudentDto studentDto = mapper.map(persistedStudent, StudentDto.class);
        return studentDto;

    }

    public StudentDto updateStudent(UpdateStudentCommand updateStudentCommand, long id){
        Student studentForUpdate =  mapper.map(updateStudentCommand, Student.class);
        studentForUpdate.setTeacher(teacherCrudService.get(updateStudentCommand.getTeacherId()));
        Student updatedStudent = studentCrudService.edit(studentForUpdate, id);
        return mapper.map(updatedStudent, StudentDto.class);

    }

    public StudentDto getStudentById(Long id){
        Student student = studentCrudService.get(id);
        return mapper.map(student, StudentDto.class);

    }

    public List<StudentDto> getAllStudents(Pageable pageable){
        Page<Student> studentPage = studentCrudService.getAll(pageable);
        List<StudentDto> studentDtos = studentPage.stream()
                .map(s -> mapper.map(s, StudentDto.class))
                .toList();
        return studentDtos;

    }


    public void deleteStudent(Long id){

        studentCrudService.delete(id);
    }
}
