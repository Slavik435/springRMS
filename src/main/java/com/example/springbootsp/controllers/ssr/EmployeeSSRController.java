package com.example.springbootsp.controllers.ssr;

import com.example.springbootsp.domain.Employee;
import com.example.springbootsp.domain.EmployeeDTO;
import com.example.springbootsp.exception.ResourceNotFoundException;
import com.example.springbootsp.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeSSRController {

    private final EmployeeService employeeService;

    @GetMapping
    public String listEmployees(Model model) {
        List<EmployeeDTO> employees = employeeService.getAllEmployees().stream()
                .map(Employee::toDto)
                .collect(Collectors.toList());
        model.addAttribute("employees", employees);
        return "listEmployees";
    }

    @GetMapping("/new")
    public String showEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee(new EmployeeDTO()));
        return "employeeForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        EmployeeDTO employee = employeeService.getEmployeeById(id)
                .map(Employee::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        model.addAttribute("employee", employee);
        return "employeeForm";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute EmployeeDTO employeeDTO) {
        employeeService.createEmployee(new Employee(employeeDTO));
        return "redirect:/employees";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(id, new Employee(employeeDTO));
        System.out.println("EmployeeDTO2: " + employeeDTO);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}