package de.yaxc.springboottutorial.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.yaxc.springboottutorial.domain.Department;
import de.yaxc.springboottutorial.services.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Objects;

import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentResource.class)
class DepartmentResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

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
        when(departmentService.saveDepartment(inputDepartment)).thenReturn(outputDepartment);

        ResultActions resultActions = mockMvc.perform(post("/api/v1/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"departmentName\": \"IT\",\n" +
                        "\t\"departmentAddress\": \"Muenchen\",\n" +
                        "\t\"departmentCode\": \"IT-007\"\n" +
                        "}"));

        // Then
        resultActions.andExpect(status().isCreated());
    }

    @Test
    void itShouldSaveDepartmentSuccessfullyHappyPath() throws Exception {
        // Given
        Department inputDepartment = Department.builder()
                .departmentName("IT")
                .departmentCode("IT-007")
                .departmentAddress("Muenchen")
                .build();
        // When
        when(departmentService.saveDepartment(inputDepartment)).thenReturn(outputDepartment);

        ResultActions resultActions = mockMvc.perform(post("/api/v1/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(inputDepartment))));

        //System.out.println(resultActions);

        // Then
        resultActions.andExpect(status().isCreated());
    }

    @Test
    void itShouldFetchDepartmentById() throws Exception {
        // Given
        // When
        when(departmentService.fetchDepartmentById(1L)).thenReturn(outputDepartment);

        mockMvc.perform(get("/api/v1/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(outputDepartment.getDepartmentName()));

        // Then
    }

    @Test
    void itShouldRetrieveAllDepartment() throws Exception {
        // Given


        // When
        ResultActions resultActions = mockMvc.perform(get("/api/v1/departments")
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void itShouldShouldRemoveDepartmentById() throws Exception {
        // Given
        Department department = Department.builder()
                .departmentName("Information Technologies")
                .departmentAddress("Berlin")
                .departmentCode("IT-009")
                .departmentId(1L)
                .build();

        // When
        ResultActions resultActions = mockMvc.perform(delete("/api/v1/departments/{departmentId}", department.getDepartmentId())
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void itShouldUpdateDepartmentById() throws Exception {
        // Given
        Department department = Department.builder()
                .departmentName("Information Technologies")
                .departmentAddress("Berlin")
                .departmentCode("IT-009")
                .departmentId(1L)
                .build();

        when(departmentService.updateDepartment(1L, department)).thenReturn(outputDepartment);


        // When
        ResultActions resultActions = mockMvc.perform(put("/api/v1/departments/{departmentId}", department.getDepartmentId())
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        resultActions.andExpect(status().isOk());
    }



    private String objectToJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            fail("Failed to convert object to json");
            return null;
        }
    }
}