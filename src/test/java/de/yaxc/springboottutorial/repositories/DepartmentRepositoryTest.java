package de.yaxc.springboottutorial.repositories;

import de.yaxc.springboottutorial.domain.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    //@Autowired
    //private TestEntityManager entityManager;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentName("Mechanical Engineering")
                .departmentCode("ME-011")
                .departmentAddress("Nürnberg")
                .build();
        //entityManager.persist(department);
        departmentRepository.save(department);
    }

    @Test
    void itShouldRetrieveDepartmentWhenDepartmentIsFoundById() {
        // Given
        // When
        Department department = departmentRepository.findById(1L).get();

        // Then
        String expected = "Mechanical Engineering";
        assertEquals(expected, department.getDepartmentName());
    }

}