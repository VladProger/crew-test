package com.company.controller;

import com.company.dto.DepartmentNameRequest;
import com.company.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("get-head")
    public String getHeadOfDepartment(@RequestBody DepartmentNameRequest departmentName){
        return departmentService.getHeadOfDepartment(departmentName.getDepartmentName());
    }

    @GetMapping("details")
    public String getDepartmentDetails(@RequestBody DepartmentNameRequest departmentName){
        return departmentService.getDepartmentDetails(departmentName.getDepartmentName());
    }

    @GetMapping("average-salary")
    public String getAverageSalary(@RequestBody DepartmentNameRequest departmentName){
        return departmentService.getAverageSalary(departmentName.getDepartmentName());
    }

    @GetMapping("employee-count")
    public Integer getEmployeeCount(@RequestBody DepartmentNameRequest departmentName){
        return departmentService.getEmployeeCount(departmentName.getDepartmentName());
    }

    @GetMapping("search/{template}")
    public String searchByTemplate(@PathVariable String template){
        return departmentService.searchByTemplate(template);
    }
}
