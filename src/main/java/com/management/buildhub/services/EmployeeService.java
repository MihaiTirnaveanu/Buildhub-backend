package com.management.buildhub.services;

import com.management.buildhub.dtos.EmployeeDto;
import com.management.buildhub.mapper.EmployeeMapper;
import com.management.buildhub.models.Employee;
import com.management.buildhub.repos.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::convertToDto).collect(Collectors.toList());
    }

    public Optional<EmployeeDto> getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.map(EmployeeMapper::convertToDto);
    }

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.convertToEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.convertToDto(savedEmployee);
    }

    public EmployeeDto updateEmployee(EmployeeDto updatedEmployeeDto) {
        Long id = updatedEmployeeDto.getId();
        Optional<Employee> existingEmployeeOptional = employeeRepository.findById(id);

        if (existingEmployeeOptional.isPresent()) {
            Employee existingEmployee = existingEmployeeOptional.get();
            Employee updatedEmployee = EmployeeMapper.convertToEntity(updatedEmployeeDto);
            existingEmployee.setFirstName(updatedEmployee.getFirstName());
            existingEmployee.setLastName(updatedEmployee.getLastName());
            existingEmployee.setTitle(updatedEmployee.getTitle());
            existingEmployee.setCrew(updatedEmployee.getCrew());

            Employee savedEmployee = employeeRepository.save(existingEmployee);
            return EmployeeMapper.convertToDto(savedEmployee);
        }

        return null;
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
