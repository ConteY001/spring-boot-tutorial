package de.yaxc.springboottutorial.services;

import de.yaxc.springboottutorial.domain.Department;
import de.yaxc.springboottutorial.exceptions.ResourceNotFoundException;

import java.util.List;

public interface DepartementService {
    Department saveDepartement(Department department);

    List<Department> fetchAllDepartments() throws ResourceNotFoundException;

    Department fetchDepartmentById(Long departmentId) throws ResourceNotFoundException;

    void deleteDepartment(Department department);

    Department updateDepartment(Long departmentId, Department department);

    Department fetchDepartmentByName(String departmentName) throws ResourceNotFoundException;
}
