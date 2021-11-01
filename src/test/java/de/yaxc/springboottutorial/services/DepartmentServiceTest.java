package de.yaxc.springboottutorial.services;

import de.yaxc.springboottutorial.domain.Department;
import de.yaxc.springboottutorial.repositories.DepartmentRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    private DepartmentService underTest;

    private Department department;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new DepartmentServiceImpl(departmentRepository);

        department = Department.builder()
                .departmentName("Agriculture")
                .departmentCode("DPT-AGR")
                .departmentAddress("München")
                .departmentId(1L)
                .build();

        //Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("Information Technologies"))
        //        .thenReturn(department);
    }

    @Test
    @DisplayName("Get Data based on valid Department Name")
    void itShouldFetchDepartmentByValidDepartmentNameHappyPath() {
        // Given
        String expectedDepartmentName = "Agriculture";
        when(departmentRepository.findByDepartmentNameIgnoreCase(expectedDepartmentName))
                .thenReturn(department);

        // When
        Department expectedDepartment = underTest.fetchDepartmentByName(expectedDepartmentName);

        // Then
        assertEquals(expectedDepartmentName, expectedDepartment.getDepartmentName());
    }

    @Test
    @DisplayName("Get Data based on invalid Department Name")
    void itShouldNotFetchDepartmentByInValidDepartmentName() {
        // Given
        String expectedDepartmentName = "IT";
        when(departmentRepository.findByDepartmentNameIgnoreCase(expectedDepartmentName))
                .thenReturn(null);

        // When
        Department expectedDepartment = underTest.fetchDepartmentByName(expectedDepartmentName);

        // Then
        assertThat(expectedDepartment).isNull();
    }

    @Test
    void itShouldShouldDeleteDepartmentById() {
        // Given
        // When
        underTest.deleteDepartment(department);

        // Then
        verify(departmentRepository, times(1)).delete(department);
    }
}