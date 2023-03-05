package net.javaguides.springboot.controller;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
        @Autowired
        private EmployeeRepository employeeRepository;

        @GetMapping
        public List<Employee> getAllEmployees(){
                return employeeRepository.findAll();
        }

        // build create employee REST API
        @PostMapping
        public Employee createEmployee(@RequestBody  Employee employee){
                return employeeRepository.save(employee);
        }

        // build get employee by id using rest api
        @GetMapping("{id}")
        public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
                Employee employee = employeeRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
                return ResponseEntity.ok(employee);
        }

        // build update method rest api
        @PutMapping("{id}")
        public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails){
                Employee updateEmployee = employeeRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id:" + id));
                updateEmployee.setFirstName(employeeDetails.getFirstName());
                updateEmployee.setLastName(employeeDetails.getLastName());
                updateEmployee.setEmailId(employeeDetails.getEmailId());

                employeeRepository.save(updateEmployee);

                return ResponseEntity.ok(updateEmployee);
        }

}
