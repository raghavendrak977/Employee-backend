package com.example.employee.service;

import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // ✅ Manual constructor instead of relying on Lombok
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee postEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees()
    {
      return employeeRepository.findAll();
    }

    public void deleteEmployee(Long id)
    {
   if(!employeeRepository.existsById(id))
   {
       throw new EntityNotFoundException("Employee with id :"+id+"not found");
   }
       employeeRepository.deleteById(id);
    }

    public Employee getEmployeeById(Long id)
    {
        return  employeeRepository.findById(id).orElse(null);


    }

    public Employee updateEmployee(Long id,Employee employee)
    {
        Optional<Employee> optional=employeeRepository.findById(id);
        if (optional.isPresent())
        {
Employee existingEmployee=optional.get();

existingEmployee.setEmail(employee.getEmail());
existingEmployee.setName((employee.getName()));
existingEmployee.setPhone(employee.getPhone());
existingEmployee.setDepartment(employee.getDepartment());
return  employeeRepository.save(existingEmployee);
        }return null;

    }




}
