package com.cognizant.spring_learn.dao;

import java.util.ArrayList;
import java.util.List;
import com.cognizant.spring_learn.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {
    private static List<Employee> EMPLOYEE_LIST;

    @SuppressWarnings("unchecked")
    public EmployeeDao() {
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        EMPLOYEE_LIST = context.getBean("employeeList", ArrayList.class);
    }

    public List<Employee> getAllEmployees() {
        return EMPLOYEE_LIST;
    }
}
