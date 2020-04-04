package Lab2;

import Lab2.domain.Nota;
import Lab2.domain.Student;
import Lab2.domain.Tema;
import Lab2.repository.NotaXMLRepository;
import Lab2.repository.StudentXMLRepository;
import Lab2.repository.TemaXMLRepository;
import Lab2.service.Service;
import Lab2.validation.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    Service service;
    StudentXMLRepository studentXMLRepository;
    TemaXMLRepository assignmentXMLRepository;
    NotaXMLRepository gradeXMLRepository;

    @Before
    public void initData(){
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        Validator<Student> studentValidator = new StudentValidator();

        studentXMLRepository = new StudentXMLRepository(studentValidator, "studenti.xml");
        assignmentXMLRepository = new TemaXMLRepository(temaValidator, "teme.xml");
        gradeXMLRepository = new NotaXMLRepository(notaValidator, "note.xml");
        service = new Service(studentXMLRepository, assignmentXMLRepository, gradeXMLRepository);
    }

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue( true );
    }

    @Test
    public void testAddStudentDuplicateId() {

        service.deleteStudent("10");
        assertNull(studentXMLRepository.findOne("10"));
        assertTrue(service.saveStudent("10", "Roxi", 932) == 1);
        assertFalse(service.saveStudent("10", "Ale", 932) == 1);

        assertEquals(studentXMLRepository.findOne("10").getNume(), "Roxi");
    }

    @Test
    public void testAddStudentToRepository(){

        service.deleteStudent("11");
        assertNull(studentXMLRepository.findOne("11"));
        assertTrue(service.saveStudent("11", "Roxi", 932) == 1);
        assertEquals(studentXMLRepository.findOne("11").getNume(), "Roxi");
    }

    @Test
    public void tc1AddStudentToRepository(){

        service.deleteStudent("11");
        assertNull(studentXMLRepository.findOne("11"));
        assertTrue(service.saveStudent("11", "Roxi", 932) == 1);
        assertEquals(studentXMLRepository.findOne("11").getNume(), "Roxi");
    }

    @Test
    public void tc2AddStudentIdNull() {
        service.deleteStudent("11");
        assertNull(studentXMLRepository.findOne("11"));
        assertFalse(service.saveStudent(null, "Roxi", 932) == 1);
        assertNull(studentXMLRepository.findOne("11"));
    }

    @Test
    public void tc3AddStudentIdEmpty() {
        service.deleteStudent("11");
        assertNull(studentXMLRepository.findOne("11"));
        assertFalse(service.saveStudent("", "Roxi", 932) == 1);
        assertNull(studentXMLRepository.findOne("11"));
    }

    @Test
    public void tc4AddStudentNameNull() {
        service.deleteStudent("11");
        assertNull(studentXMLRepository.findOne("11"));
        assertFalse(service.saveStudent("11", null, 932) == 1);
        assertNull(studentXMLRepository.findOne("11"));
    }

    @Test
    public void tc5AddStudentNameEmpty() {
        service.deleteStudent("11");
        assertNull(studentXMLRepository.findOne("11"));
        assertFalse(service.saveStudent("11", "", 932) == 1);
        assertNull(studentXMLRepository.findOne("11"));
    }


    @Test
    public void tc6AddStudentGroup110Invalid() {
        service.deleteStudent("11");
        assertNull(studentXMLRepository.findOne("11"));
        assertFalse(service.saveStudent("11", "", 932) == 1);
        assertNull(studentXMLRepository.findOne("11"));
    }

    @Test
    public void tc7AddStudentGroup938Invalid() {
        service.deleteStudent("11");
        assertNull(studentXMLRepository.findOne("11"));
        assertFalse(service.saveStudent("11", "Roxi", 938) == 1);
        assertNull(studentXMLRepository.findOne("11"));
    }


    @Test
    public void tc8AddStudentGroup111Valid() {
        service.deleteStudent("11");
        assertNull(studentXMLRepository.findOne("11"));
        assertTrue(service.saveStudent("11", "Roxi", 111) == 1);
        assertEquals(studentXMLRepository.findOne("11").getGrupa(), 111);
    }

    @Test
    public void tc9AddStudentGroup937Valid() {
        service.deleteStudent("11");
        assertNull(studentXMLRepository.findOne("11"));
        assertTrue(service.saveStudent("11", "Roxi", 937) == 1);
        assertEquals(studentXMLRepository.findOne("11").getGrupa(), 937);
    }
}
