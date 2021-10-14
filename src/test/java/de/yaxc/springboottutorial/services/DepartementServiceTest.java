package de.yaxc.springboottutorial.services;

import de.yaxc.springboottutorial.domain.Department;
import de.yaxc.springboottutorial.repositories.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartementServiceTest {

    @Autowired
    private DepartementService departementService;

    @Mock
    private DepartmentRepository departmentRepository;

    private Department department;


    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentName("Information Technologies")
                .departmentCode("IT-09")
                .departmentAddress("Nürnberg")
                .departmentId(1L)
                .build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("Information Technologies"))
                .thenReturn(department);
    }

    @Test
    @DisplayName("Get Data based on valid Department Name")
    void itShouldFetchDepartmentByNameByValidDepartmentName() {
        // Given
        String departmentName = "Information Technologies";

        // When
        Department found = departementService.fetchDepartmentByName(departmentName);

        // Then
        assertEquals(departmentName, found.getDepartmentName());
    }
}