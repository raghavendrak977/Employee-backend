package com.example.employee.controller;

import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public Employee postEmployee(@RequestBody Employee employee) {
        return employeeService.postEmployee(employee);
    }
    @GetMapping("/employees")
    public List<Employee> getAllEmployees()
    {
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity deleteEmployee(@PathVariable  Long id)
    {
        try{
employeeService.deleteEmployee(id);
return  new ResponseEntity("Employee with id "+id+"deleted succesfully", HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
return  new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


@GetMapping("/employee/{id}")
    public ResponseEntity getEmployeeById(@PathVariable Long id)
    {
        Employee emp=employeeService.getEmployeeById(id);

        if(emp ==null)
        {
          return  ResponseEntity.notFound().build();
        }
        else {
            return  ResponseEntity.ok(emp);
        }
    }


    @PatchMapping ("/employee/{id}")
    public ResponseEntity updateEmployee(@PathVariable Long id,@RequestBody Employee employee)
    {
        Employee updatedEmp=employeeService.updateEmployee(id,employee);

        if(updatedEmp ==null)
        {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return  ResponseEntity.ok(updatedEmp);
    }


}
