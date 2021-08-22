package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("This email already exists!");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean isStudentExist = studentRepository.existsById(studentId);

        if (!isStudentExist) {
            throw new IllegalStateException("Student with id \"" + studentId + "\" is not exist!");
        }

        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id \"" + studentId + "\" is not exist!"));

        if (name != null && name.length() > 0
                && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0
                && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> emailOptional = studentRepository.findStudentByEmail(email);

            if (emailOptional.isPresent()) {
                throw new IllegalStateException("This email already exist!");
            }
            student.setEmail(email);
        }
    }
}
