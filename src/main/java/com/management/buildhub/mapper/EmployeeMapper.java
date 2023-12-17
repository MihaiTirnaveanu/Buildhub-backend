package com.management.buildhub.mapper;

import com.management.buildhub.dtos.EmployeeDto;
import com.management.buildhub.models.Crew;
import com.management.buildhub.models.Employee;

public class EmployeeMapper {

    public static EmployeeDto convertToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setTitle(employee.getTitle());
        if (employee.getCrew() != null) {
            employeeDto.setCrewId(employee.getCrew().getId());
        }
        return employeeDto;
    }

    public static Employee convertToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setTitle(employeeDto.getTitle());

        if (employeeDto.getCrewId() != null) {
            Crew crew = new Crew();
            crew.setId(employeeDto.getCrewId());
            employee.setCrew(crew);
        }

        return employee;
    }
}
