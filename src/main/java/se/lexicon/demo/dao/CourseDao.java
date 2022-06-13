package se.lexicon.demo.dao;


import se.lexicon.demo.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseDao {

    Course save(Course course);

    Optional<Course> findById(int id);

    List<Course> findAll();

    void remove(Course course);

    Course update(Course course);
}
