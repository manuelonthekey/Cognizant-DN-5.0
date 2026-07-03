# Hands on 6 - Find a country based on country code

Rebuilt using the actual project structure from Hands on 1 / Hands on 5 of the
lab document (Spring Boot app, artifact `orm-learn`, base package
`com.cognizant.ormlearn`, schema `ormlearn`, table `country` with columns
`co_code` / `co_name`).

## What's inside this zip

```
src/main/java/com/cognizant/ormlearn/
 ├── OrmLearnApplication.java              (UPDATED - added getAllCountriesTest())
 ├── model/Country.java                    (reference only - skip if you already have it)
 ├── repository/CountryRepository.java     (reference only - skip if you already have it)
 └── service/
      ├── CountryService.java              (UPDATED - added findCountryByCode())
      └── exception/CountryNotFoundException.java   (NEW)
src/main/resources/application.properties  (reference only, matches Hands on 1 config)
```

## What you actually need to copy into your EXISTING project

You already built the project in Hands on 1 and populated the country table in
Hands on 5, so only copy these three things in:

### 1. NEW FILE
`service/exception/CountryNotFoundException.java` → drop in as-is.

### 2. UPDATE `service/CountryService.java`
Add this method to your existing `CountryService` class (keep your existing
`getAllCountries()` method and the `@Autowired CountryRepository` field):

```java
@Transactional
public Country findCountryByCode(String countryCode) throws CountryNotFoundException {

    Optional<Country> result = countryRepository.findById(countryCode);

    if (!result.isPresent()) {
        throw new CountryNotFoundException("Country not found for code: " + countryCode);
    }

    Country country = result.get();

    return country;
}
```

Imports needed:
```java
import java.util.Optional;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
```
(`@Transactional` and `Country` should already be imported from Hands on 1.)

### 3. UPDATE `OrmLearnApplication.java`
Add the test method (kept as `getAllCountriesTest()` per the exercise
instructions, even though it now exercises `findCountryByCode`) and call it
from `main()`:

```java
private static void getAllCountriesTest() {
    LOGGER.info("Start");
    try {
        Country country = countryService.findCountryByCode("IN");
        LOGGER.debug("Country:{}", country);
    } catch (CountryNotFoundException e) {
        LOGGER.error("Country not found: {}", e.getMessage());
    }
    LOGGER.info("End");
}
```

And add the call inside `main()`, after `testGetAllCountries();`:
```java
getAllCountriesTest();
```

Add the import:
```java
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
```

The `model/Country.java`, `repository/CountryRepository.java`, and
`application.properties` files are included only as fallback reference in
case your project doesn't already have them. If your Hands on 1/5 project
already compiles and the `country` table has data, ignore these.

## Why @Transactional matters

Spring Data JPA repository calls (like `findById`) need an active Hibernate
`Session` / JPA `EntityManager` to run against. Annotating the service method
with `@Transactional` makes Spring automatically:

- Open a Hibernate session and start a transaction before the method runs
- Bind that session to the current thread so the repository can use it
- Commit the transaction (and close the session) when the method returns
  normally
- Roll back the transaction if a runtime exception is thrown

Without `@Transactional`, you'd have to manually open/close sessions and
begin/commit/rollback transactions yourself (the old-school Hibernate way) -
Spring is automating exactly that.

## Expected output

Running `OrmLearnApplication.main()` should log a `Country` object for code
`IN`, e.g.:

```
Country:Country [code=IN, name=India]
```

Try passing a code that doesn't exist (e.g. `"XX"`) to see the
`CountryNotFoundException` get caught and logged as an error.
