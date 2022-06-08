package se.lexicon.demo.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import se.lexicon.demo.entity.Student;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest // spring boot test is used to test the unit test in jpa and entity manager
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // it configures the test database
@DirtiesContext // this tells the Spring to clear the application context after the test has sun
public class StudentDaoTest {

    @Autowired
    private StudentDao testObject;

    @Test
    void save() {
        Student actualData = testObject.save(new Student("Test", "Test", "test.test@test.se", LocalDate.parse("2020-01-01")));
        Student expectedData = testObject.findById(1).orElse(null);
        assertEquals(expectedData, actualData);
    }

    // todo: implement other tests

}
