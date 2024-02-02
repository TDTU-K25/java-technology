package com.example.ex2.controllers;

import com.example.ex2.models.Employee;
import com.example.ex2.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    public EmployeeService employeeService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("employees", employeeService.getAll());
        return "employees/index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/add";
    }

    @PostMapping("/add")
    public RedirectView addEmployee(@ModelAttribute Employee employee) {
        employeeService.add(employee);
        return new RedirectView("/employees");
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable Integer id) {
        Employee employeeFound = employeeService.getById(id);
        model.addAttribute("employee", employeeFound);
        return "employees/edit";
    }

    @PostMapping("/edit")
    public RedirectView editEmployee(@ModelAttribute Employee employee) {
        employeeService.edit(employee);
        return new RedirectView("/employees");
    }

    @PostMapping("/delete/{id}")
    public RedirectView deleteEmployee(Model model, @PathVariable Integer id) {
        employeeService.delete(id);
        return new RedirectView("/employees");
    }
}