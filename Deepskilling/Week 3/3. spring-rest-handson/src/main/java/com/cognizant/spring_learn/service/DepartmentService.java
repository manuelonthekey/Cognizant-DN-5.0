package com.cognizant.spring_learn.service;

import java.util.List;
import com.cognizant.spring_learn.Department;
import com.cognizant.spring_learn.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }
}
