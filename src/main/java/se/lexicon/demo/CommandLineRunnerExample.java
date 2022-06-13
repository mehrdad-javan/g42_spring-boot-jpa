package se.lexicon.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.demo.dao.AddressDao;
import se.lexicon.demo.dao.BookDao;
import se.lexicon.demo.dao.CourseDao;
import se.lexicon.demo.dao.StudentDao;
import se.lexicon.demo.entity.Address;
import se.lexicon.demo.entity.Book;
import se.lexicon.demo.entity.Course;
import se.lexicon.demo.entity.Student;

import java.time.LocalDate;

@Component
public class CommandLineRunnerExample implements CommandLineRunner {

    StudentDao studentDao;
    AddressDao addressDao;
    BookDao bookDao;
    CourseDao courseDao;


    @Autowired
    public CommandLineRunnerExample(StudentDao studentDao, AddressDao addressDao, BookDao bookDao, CourseDao courseDao) {
        this.studentDao = studentDao;
        this.addressDao = addressDao;
        this.bookDao = bookDao;
        this.courseDao = courseDao;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Address address1 = new Address("Växjö", "Teleborg", "35256");
        Address insertedAddressRow1 = addressDao.save(address1);


        Student student1 = new Student(
                "Mehrdad",
                "Javan",
                "mehrdad.javan@lexicon.se",
                LocalDate.parse("1989-02-27"), insertedAddressRow1);
        Student insertedStudentRow1 = studentDao.save(student1);
        System.out.println("insertedStudentRow1 = " + insertedStudentRow1);


        // CascadeType.PERSIST
        Student student2 = new Student(
                "Simon",
                "Elbrink",
                "simon.elbrink@lexicon.se",
                LocalDate.parse("1992-01-01"), new Address("Test", "Test", "555555"));
        Student insertedStudentRow2 = studentDao.save(student2);
        System.out.println("insertedStudentRow2 = " + insertedStudentRow2);

        // CascadeType.REMOVE
        //studentDao.remove(insertedStudentRow1);


        // CascadeType.MERGE
        insertedStudentRow2.setFirstName("TEST");
        insertedStudentRow2.getAddress().setCity("ABCD");
        Student updatedStudentRow2 = studentDao.update(insertedStudentRow2);
        System.out.println("updatedStudentRow2 = " + updatedStudentRow2);

        // Bi directional
        Student student3 = new Student(
                "Test3",
                "Test3",
                "test3.test3£test.se",
                LocalDate.now());
        student3.setAddress(new Address("test3","test3 street","33333" ));
        Student insertedStudentRow3 = studentDao.save(student3);
        System.out.println("Address.getStudent()" + insertedStudentRow3.getAddress().getStudent());


        // Book Dao
        Book insertedBookRowJavaSE = bookDao.save(new Book("Java SE"));
        Book insertedBookRowJavaEE = bookDao.save(new Book("Java EE"));
        Book insertedBookRowSpring = bookDao.save(new Book("Spring"));
        Book insertedBookRowJPA = bookDao.save(new Book("JPA"));

        // ManyToOne & ManyToOne
        insertedBookRowJavaSE.setStudent(insertedStudentRow1);
        insertedBookRowJavaEE.setStudent(insertedStudentRow1);

        insertedStudentRow2.borrowBook(insertedBookRowSpring);
        insertedStudentRow2.borrowBook(insertedBookRowJPA);

        insertedStudentRow2.returnBook(insertedBookRowSpring);


        Course java = courseDao.save(new Course("Java"));
        Course c = courseDao.save(new Course("C#"));
        Course react = courseDao.save(new Course("React"));

        java.addStudent(insertedStudentRow1);
        java.addStudent(insertedStudentRow2);

        c.addStudent(insertedStudentRow1);
        c.addStudent(insertedStudentRow3);

        insertedStudentRow3.addCourse(react);






    }

}
