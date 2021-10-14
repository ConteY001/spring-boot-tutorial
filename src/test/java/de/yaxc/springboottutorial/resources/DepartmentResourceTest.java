package de.yaxc.springboottutorial.resources;

import de.yaxc.springboottutorial.domain.Department;
import de.yaxc.springboottutorial.services.DepartementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentResource.class)
class DepartmentResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartementService departementService;

    private Department outputDepartment;

    @BeforeEach
    void setUp() {
        outputDepartment = Department.builder()
                .departmentName("IT")
                .departmentCode("IT-007")
                .departmentAddress("Muenchen")
                .departmentId(1L)
                .build();
    }

    @Test
    void itShouldSaveDepartment() throws Exception {
        // Given
        Department inputDepartment = Department.builder()
                .departmentName("IT")
                .departmentCode("IT-007")
                .departmentAddress("Muenchen")
                .build();
        // When
        Mockito.when(departementService.saveDepartement(inputDepartment)).thenReturn(outputDepartment);

        // Then
        mockMvc.perform(post("/api/v1/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"departmentName\": \"IT\",\n" +
                        "\t\"departmentAddress\": \"Muenchen\",\n" +
                        "\t\"departmentCode\": \"IT-007\"\n" +
                        "}"))
                .andExpect(status().isCreated());
    }

    @Test
    void itShouldFetchDepartmentById() throws Exception {
        // Given
        // When
        Mockito.when(departementService.fetchDepartmentById(1L)).thenReturn(outputDepartment);

        // Then
        mockMvc.perform(get("/api/v1/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(outputDepartment.getDepartmentName()));

    }
}