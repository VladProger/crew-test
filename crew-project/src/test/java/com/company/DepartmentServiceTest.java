package com.company;

import static org.mockito.Mockito.when;

import com.company.entity.DepartmentEntity;
import com.company.entity.LectorEntity;
import com.company.repository.DepartmentRepository;
import com.company.repository.LectorRepository;
import com.company.service.DepartmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class DepartmentServiceTest {
    private DepartmentService departmentService;
    private DepartmentRepository departmentRepository;
    private LectorRepository lectorRepository;
    private DepartmentEntity departmentEntity;

    @BeforeEach
    void setUp() {
        departmentRepository = Mockito.mock(DepartmentRepository.class);
        lectorRepository = Mockito.mock(LectorRepository.class);
        departmentService = new DepartmentService(departmentRepository, lectorRepository);
        departmentEntity = initializeEntity();
    }

    @Test
    public void getHeadOfDepartment() {
        when(departmentRepository.findByName("Computer Science")).thenReturn(departmentEntity);
        String result = departmentService.getHeadOfDepartment("Computer Science");
        Assertions.assertEquals(result, "Head of Computer Science department is Ivan Ivanov");
    }

    private DepartmentEntity initializeEntity() {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(1);
        departmentEntity.setDepartmentHead("Ivan Ivanov");
        departmentEntity.setName("Computer Science");
        return departmentEntity;
    }
}
