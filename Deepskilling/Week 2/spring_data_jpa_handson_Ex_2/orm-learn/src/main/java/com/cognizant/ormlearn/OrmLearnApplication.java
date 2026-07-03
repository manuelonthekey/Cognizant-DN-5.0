package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.repository.StockRepository;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class OrmLearnApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SkillService skillService;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        // Uncomment to keep the H2 console/context alive for manual poking around.
        // context.close();
    }

    @Override
    public void run(String... args) {
        LOGGER.info("=========== QUERY METHODS DEMO ===========");
        testCountrySearch();
        testCountrySearchSorted();
        testCountryAlphabetIndex();
        testStockByCodeAndDateRange();
        testStockGreaterThanPrice();
        testTopVolumeStocks();
        testLowestClosingStocks();

        LOGGER.info("=========== O/R MAPPING DEMO ===========");
        testGetEmployee();
        testGetDepartment();
        testAddEmployee();
        testUpdateEmployee();
        testAddSkillToEmployee();
    }

    // ------------------ Query Methods: Country ------------------

    private void testCountrySearch() {
        LOGGER.info("-- findByNameContaining(\"ou\") --");
        List<Country> countries = countryRepository.findByNameContaining("ou");
        countries.forEach(c -> LOGGER.debug("{}", c));
    }

    private void testCountrySearchSorted() {
        LOGGER.info("-- findByNameContainingOrderByNameAsc(\"ou\") --");
        List<Country> countries = countryRepository.findByNameContainingOrderByNameAsc("ou");
        countries.forEach(c -> LOGGER.debug("{}", c));
    }

    private void testCountryAlphabetIndex() {
        LOGGER.info("-- findByNameStartingWith(\"Z\") --");
        List<Country> countries = countryRepository.findByNameStartingWith("Z");
        countries.forEach(c -> LOGGER.debug("{}", c));
    }

    // ------------------ Query Methods: Stock ------------------

    private void testStockByCodeAndDateRange() {
        LOGGER.info("-- Facebook stock, September 2019 --");
        List<Stock> stocks = stockRepository.findByCodeAndDateBetween(
                "FB", LocalDate.of(2019, 9, 1), LocalDate.of(2019, 9, 30));
        stocks.forEach(s -> LOGGER.debug("{}", s));
    }

    private void testStockGreaterThanPrice() {
        LOGGER.info("-- Google stock, close > 1250 --");
        List<Stock> stocks = stockRepository.findByCodeAndCloseGreaterThan("GOOGL", new BigDecimal("1250"));
        stocks.forEach(s -> LOGGER.debug("{}", s));
    }

    private void testTopVolumeStocks() {
        LOGGER.info("-- Top 3 highest volume days --");
        List<Stock> stocks = stockRepository.findTop3ByOrderByVolumeDesc();
        stocks.forEach(s -> LOGGER.debug("{}", s));
    }

    private void testLowestClosingStocks() {
        LOGGER.info("-- Netflix, 3 lowest closing days --");
        List<Stock> stocks = stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
        stocks.forEach(s -> LOGGER.debug("{}", s));
    }

    // ------------------ O/R Mapping: ManyToOne, OneToMany, ManyToMany ------------------

    private void testGetEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee:{}", employee);
        LOGGER.debug("Department:{}", employee.getDepartment());
        LOGGER.debug("Skills:{}", employee.getSkillList());
        LOGGER.info("End");
    }

    private void testGetDepartment() {
        LOGGER.info("Start");
        Department department = departmentService.get(1);
        LOGGER.debug("Department:{}", department);
        LOGGER.debug("Employees:{}", department.getEmployeeList());
        LOGGER.info("End");
    }

    private void testAddEmployee() {
        LOGGER.info("Start");
        Employee employee = new Employee();
        employee.setName("Emma Davis");
        employee.setSalary(60000.00);
        employee.setPermanent(true);
        employee.setDateOfBirth(new java.util.Date());

        Department department = departmentService.get(1);
        employee.setDepartment(department);

        employeeService.save(employee);
        LOGGER.debug("Employee added:{}", employee);
        LOGGER.info("End");
    }

    private void testUpdateEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        Department department = departmentService.get(2);
        employee.setDepartment(department);

        employeeService.save(employee);
        LOGGER.debug("Employee updated:{}", employee);
        LOGGER.info("End");
    }

    private void testAddSkillToEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(3);
        Skill skill = skillService.get(3);

        Set<Skill> skills = employee.getSkillList();
        if (skills == null) {
            skills = new HashSet<>();
        }
        skills.add(skill);
        employee.setSkillList(skills);

        employeeService.save(employee);
        LOGGER.debug("Skill added to employee:{}", employee);
        LOGGER.info("End");
    }
}
