package com.phychologyServer.phychologyServer.controller

import com.phychologyServer.phychologyServer.model.employee.Employee
import com.phychologyServer.phychologyServer.model.employee.EmployeeDAO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/employee")
class EmployeeController (private val employeeDAO: EmployeeDAO){

    @GetMapping("/get-all")
    fun getAllEmployees(): List<Employee> {
        return employeeDAO.getAllEmployees()
    }

    @PostMapping("/save")
    fun saveEmployee(@RequestBody employee: Employee): Employee{
        return employeeDAO.save(employee)
    }
}