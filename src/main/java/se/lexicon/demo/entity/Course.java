package se.lexicon.demo.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    private String name;

    @ManyToMany
    @JoinTable(name = "students_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        if (students == null) students = new ArrayList<>();
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }




    // convenience methods for manipulating with student list

    public void addStudent(Student student){
        if (student == null) throw new IllegalArgumentException("student data is null");
        if (students == null) students = new ArrayList<>();

        if (!students.contains(student)) students.add(student);
        //student.getCourses().add(this);

    }

    public void removeStudent(Student student){
        if (student == null) throw new IllegalArgumentException("student data is null");
        if (students == null) students = new ArrayList<>();

        //student.getCourses().remove(this);
        students.remove(student);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && Objects.equals(name, course.name) && Objects.equals(students, course.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, students);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
