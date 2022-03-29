package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    String newId = "100";

    @org.junit.jupiter.api.Test
    void saveAssignment_withValidData() {
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        assertEquals(service.saveTema(this.newId, "service_test_new_id_should_add", 9, 7), 1, "Nu sunt egale frate");
        service.deleteTema(this.newId);
    }

    @org.junit.jupiter.api.Test
    void saveAssignment_existentId() {
        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        assertEquals(service.saveTema("1", "service_test_new_id_should_add", 9, 7), 0, "Nu sunt egale frate");
    }
}