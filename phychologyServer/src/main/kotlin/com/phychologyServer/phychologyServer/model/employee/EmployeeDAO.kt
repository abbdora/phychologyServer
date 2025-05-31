package com.phychologyServer.phychologyServer.model.employee

import org.springframework.stereotype.Service

@Service
class EmployeeDAO (
    private val repository: EmployeeRepository
){
    fun save(employee: Employee) = repository.save(employee)

    fun getAllEmployees(): List<Employee> = repository.findAll().toList()

    fun delete(employeeId: Int) = repository.deleteById(employeeId)
}