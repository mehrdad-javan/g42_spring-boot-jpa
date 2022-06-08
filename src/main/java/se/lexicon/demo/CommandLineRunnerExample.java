package se.lexicon.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.demo.dao.StudentDao;
import se.lexicon.demo.entity.Student;

import java.time.LocalDate;

//@Component
public class CommandLineRunnerExample /*implements CommandLineRunner*/ {

    /*StudentDao studentDao;

    @Autowired
    public CommandLineRunnerExample(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void run(String... args) throws Exception {
        Student student1 = new Student(
                "Mehrdad",
                "Javan",
                "mehrdad.javan@lexicon.se",
                LocalDate.parse("1989-02-27"));
        Student insertedRow = studentDao.save(student1);
        System.out.println("insertedRow = " + insertedRow);
    }*/
}
