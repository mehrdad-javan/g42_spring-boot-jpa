package se.lexicon.demo.dao;

import se.lexicon.demo.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {

    Student save(Student student);

    Optional<Student> findById(int id);

    List<Student> findAll();

    List<Student> findByFirstName(String firstName);

    void remove(Student student);

    Student update(Student student);

    List<Student> saveStudents(List<Student> students);
}
