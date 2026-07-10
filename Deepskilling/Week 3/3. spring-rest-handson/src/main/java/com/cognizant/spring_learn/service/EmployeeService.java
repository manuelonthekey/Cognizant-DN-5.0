package com.cognizant.spring_learn.service;

import java.util.List;
import com.cognizant.spring_learn.Employee;
import com.cognizant.spring_learn.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }
}
