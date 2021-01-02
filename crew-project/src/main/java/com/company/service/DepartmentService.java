package com.company.service;

import com.company.dto.Degree;
import com.company.entity.DepartmentEntity;
import com.company.entity.LectorEntity;
import com.company.exception.BadRequestException;
import com.company.repository.DepartmentRepository;
import com.company.repository.LectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    public String getHeadOfDepartment(String departmentName) {
        DepartmentEntity departmentEntity = getDepartmentByName(departmentName);
        return "Head of " + departmentName + " department is " +
                departmentEntity.getDepartmentHead();
    }

    public String getDepartmentDetails(String departmentName) {
        DepartmentEntity departmentEntity = getDepartmentByName(departmentName);

        List<LectorEntity> lectorEntities = lectorRepository.findAllByDepartment(departmentEntity.getId());
        return "Assistans - " +
                lectorEntities.stream().filter(x -> x.getDegree() == Degree.ASSISTANT.getValue()).count() +
                "\nAssociate professors - " +
                lectorEntities.stream().filter(x -> x.getDegree() == Degree.ASSOCIATE_PROFESSOR.getValue()).count() +
                "\nProfessors - " +
                lectorEntities.stream().filter(x -> x.getDegree() == Degree.PROFESSOR.getValue()).count();
    }

    public String getAverageSalary(String departmentName) {
        DepartmentEntity departmentEntity = getDepartmentByName(departmentName);
        List<LectorEntity> lectorEntities = lectorRepository.findAllByDepartment(departmentEntity.getId());
        if (lectorEntities.size() == 0)
            return "0";
        double sumSalary = lectorEntities.stream().mapToDouble(LectorEntity::getSalary).sum();
        return "The average salary of " + departmentName + " is " + sumSalary / lectorEntities.size();
    }

    public Integer getEmployeeCount(String departmentName) {
        DepartmentEntity departmentEntity = getDepartmentByName(departmentName);
        return lectorRepository.findAllByDepartment(departmentEntity.getId()).size();
    }

    public String searchByTemplate(String template) {
        if (template == null || template.isEmpty())
            throw new BadRequestException("Template is empty");
        List<LectorEntity> entities = lectorRepository.findAllByNameIsLike(template);
        StringBuilder names = new StringBuilder();
        for (LectorEntity entity : entities) {
            names.append(entity.getName()).append(",");
        }
        if (!names.toString().equals(""))
            names.deleteCharAt(names.lastIndexOf(","));
        return names.toString();
    }

    private DepartmentEntity getDepartmentByName(String departmentName) {
        if (departmentName == null || departmentName.isEmpty())
            throw new BadRequestException("Department name is empty");
        DepartmentEntity departmentEntity = departmentRepository.findByName(departmentName);
        if (departmentEntity == null)
            return new DepartmentEntity();
        return departmentEntity;
    }
}
