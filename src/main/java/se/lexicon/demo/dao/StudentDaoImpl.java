package se.lexicon.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.demo.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public Student save(Student student) {
        if (student == null) throw new IllegalArgumentException("student is null");
        entityManager.persist(student);
        return student;
    }

    @Override
    public Optional<Student> findById(int id) {
        if (id <= 0) throw new IllegalArgumentException("is is not valid");
        Student result = entityManager.find(Student.class, id); // select * from student where id = ?
        return Optional.ofNullable(result);
    }

    @Override
    public List<Student> findAll() {
      Query selectQuery = entityManager.createQuery("select s from Student s");
     return selectQuery.getResultList();
    }

    @Override
    public List<Student> findByFirstName(String firstName) {
        if (firstName == null) throw new IllegalArgumentException("firstName is null");
       Query selectByNameQuery = entityManager.createQuery("select s from Student  s where s.firstName = :fn");
        selectByNameQuery.setParameter("fn",firstName);
        return selectByNameQuery.getResultList();
    }

    @Transactional
    @Override
    public void remove(Student student) {
        findById(student.getId()).orElseThrow(() -> new IllegalArgumentException("data not found exception"));
        entityManager.remove(student);
    }

    @Transactional
    @Override
    public Student update(Student student) {
        return entityManager.merge(student);
    }

    @Transactional
    @Override
    public List<Student> saveStudents(List<Student> students) {
        for (Student student: students){
            save(student);
        }
        return students;
    }
}
