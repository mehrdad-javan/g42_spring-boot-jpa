package se.lexicon.demo.dao;

import org.springframework.stereotype.Repository;
import se.lexicon.demo.entity.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseDaoImpl implements CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Course save(Course course) {
        entityManager.persist(course);
        return course;
    }

    @Override
    public Optional<Course> findById(int id) {
        Course course = entityManager.find(Course.class, id);
        return Optional.ofNullable(course);
    }

    @Override
    public List<Course> findAll() {
        return  entityManager.createQuery("select c from Course c").getResultList();
    }

    @Override
    public void remove(Course course) {
        // find by id
        entityManager.remove(course);

    }

    @Override
    public Course update(Course course) {
        // find by id
        return entityManager.merge(course);
    }
}
