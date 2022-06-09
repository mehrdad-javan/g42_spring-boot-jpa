package se.lexicon.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.demo.dao.AddressDao;
import se.lexicon.demo.dao.StudentDao;
import se.lexicon.demo.entity.Address;
import se.lexicon.demo.entity.Student;

import java.time.LocalDate;

@Component
public class CommandLineRunnerExample implements CommandLineRunner {

    StudentDao studentDao;
    AddressDao addressDao;

    @Autowired
    public CommandLineRunnerExample(StudentDao studentDao, AddressDao addressDao) {
        this.studentDao = studentDao;
        this.addressDao = addressDao;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Address address1 = new Address("Växjö","Teleborg","35256");
        Address insertedAddressRow1 = addressDao.save(address1);


        Student student1 = new Student(
                "Mehrdad",
                "Javan",
                "mehrdad.javan@lexicon.se",
                LocalDate.parse("1989-02-27"),insertedAddressRow1);
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
    }

}
