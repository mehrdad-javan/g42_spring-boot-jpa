package se.lexicon.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
//@Table(name = "TBL_STUDENT")
public class Student { // TBL_STUDENT

    @Id // primary key for id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // means auto_increment
    private int id;

    @Column(nullable = false, length = 100) // not null varchar(100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, length = 200, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate birthDate;

    private boolean status;

    private LocalDateTime registerDate;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    // foreign key (address_id) references address(id)
    private Address address;

    @OneToMany(mappedBy = "student")
    private List<Book> borrowedBooks;

    //@ManyToMany(mappedBy = "students")
    @ManyToMany
    @JoinTable(name = "students_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

    public Student() {
        this.registerDate = LocalDateTime.now();
    }

    public Student(String firstName, String lastName, String email, LocalDate birthDate) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Student(String firstName, String lastName, String email, LocalDate birthDate, Address address) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        setAddress(address);
    }

    public Student(int id, String firstName, String lastName, String email, LocalDate birthDate) {
        this();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if (address != null) {
            address.setStudent(this);
        }
        this.address = address;
    }

    public List<Book> getBorrowedBooks() {
        if (borrowedBooks == null) borrowedBooks = new ArrayList<>();
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public List<Course> getCourses() {
        if (courses == null) courses = new ArrayList<>();
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }


    // convenience methods for manipulating with list
    public void borrowBook(Book book) {
        if (book == null) throw new IllegalArgumentException("book data is null");
        if (borrowedBooks == null) borrowedBooks = new ArrayList<>();

        borrowedBooks.add(book);
        book.setStudent(this);

    }

    public void returnBook(Book book) {
        if (book == null) throw new IllegalArgumentException("book data is null");
        if (borrowedBooks != null) {
            book.setStudent(null);
            borrowedBooks.remove(book);
        }
    }


    public void addCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course data is null");
        if (courses == null) courses = new ArrayList<>();

        if (!courses.contains(course)) courses.add(course);
        //course.getStudents().add(this);

    }


    public void removeCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course data is null");
        if (courses == null) courses = new ArrayList<>();
        //course.getStudents().remove(this);
        courses.remove(course);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && status == student.status && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(email, student.email) && Objects.equals(birthDate, student.birthDate) && Objects.equals(registerDate, student.registerDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, birthDate, status, registerDate);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", status=" + status +
                ", registerDate=" + registerDate +
                '}';
    }
}
