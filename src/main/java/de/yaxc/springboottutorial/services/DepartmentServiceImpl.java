package de.yaxc.springboottutorial.services;

import de.yaxc.springboottutorial.domain.Department;
import de.yaxc.springboottutorial.exceptions.ResourceNotFoundException;
import de.yaxc.springboottutorial.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartementService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department saveDepartement(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws ResourceNotFoundException {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if(!optionalDepartment.isPresent()) {
            throw new ResourceNotFoundException(String.format("Department with [%s] is not available ", departmentId));
        }
        return optionalDepartment.get();
    }

    @Override
    public void deleteDepartment(Department department) {
        departmentRepository.delete(department);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department departmentFromDB = departmentRepository.findById(departmentId).get();
        if(Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())) {
            departmentFromDB.setDepartmentName(department.getDepartmentName());
        }
        if(Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())) {
            departmentFromDB.setDepartmentCode(department.getDepartmentCode());
        }
        if(Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())) {
            departmentFromDB.setDepartmentAddress(department.getDepartmentAddress());
        }
        return departmentRepository.save(departmentFromDB);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}
