package com.cognizant.spring_learn.controller;

import java.util.List;
import com.cognizant.spring_learn.Country;
import com.cognizant.spring_learn.service.CountryService;
import com.cognizant.spring_learn.service.exception.CountryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/country")
    public Country getCountryIndia() {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        return context.getBean("in", Country.class);
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping({"/countries/{code}", "/country/{code}"})
    public Country getCountry(@PathVariable("code") String code) throws CountryNotFoundException {
        return countryService.getCountry(code);
    }
}
