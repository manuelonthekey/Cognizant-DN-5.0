package com.cognizant.spring_learn.service;

import java.util.ArrayList;
import java.util.List;
import com.cognizant.spring_learn.Country;
import com.cognizant.spring_learn.service.exception.CountryNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    private List<Country> countries;

    @SuppressWarnings("unchecked")
    public CountryService() {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        this.countries = context.getBean("countryList", ArrayList.class);
    }

    public Country getCountry(String code) throws CountryNotFoundException {
        return countries.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(CountryNotFoundException::new);
    }

    public List<Country> getAllCountries() {
        return countries;
    }
}
