package com.example.ex2.services;

import com.example.ex2.models.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    void add(Employee employee);

    Employee getById(Integer id);

    void edit(Employee employee);

    void delete(Integer id);
}
