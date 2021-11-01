package de.yaxc.springboottutorial.resources;

import de.yaxc.springboottutorial.domain.Department;
import de.yaxc.springboottutorial.services.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentResource {
    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentResource.class);

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<Department> saveDepartment(@Valid @RequestBody Department department) {
        LOGGER.info("Inside saveDepartment of DepartmentResource");
        Department savedDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Department>> fetchAllDepartments() {
        LOGGER.info("Inside fetchAllDepartments of DepartmentResource");
        List<Department> departments = departmentService.fetchAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> fetchDepartmentById(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Inside fetchDepartmentById of DepartmentResource");
        Department department = departmentService.fetchDepartmentById(departmentId);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Map<String, Boolean>> deleteDepartmentById(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Inside deleteDepartmentById of DepartmentResource");
        Department department = departmentService.fetchDepartmentById(departmentId);
        departmentService.deleteDepartment(department);

        Map<String, Boolean> map = new HashMap<>();
        map.put("Department deleted successfully.", Boolean.TRUE);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("departmentId") Long departmentId, @Valid @RequestBody Department department) {
        LOGGER.info("Inside updateDepartment of DepartmentResource");
        Department updatedDepartment = departmentService.updateDepartment(departmentId, department);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Department> fetchDepartmentByName(@PathVariable("name") String departmentName) {
        LOGGER.info("Inside fetchDepartmentByName of DepartmentResource");
        Department department = departmentService.fetchDepartmentByName(departmentName);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
}
