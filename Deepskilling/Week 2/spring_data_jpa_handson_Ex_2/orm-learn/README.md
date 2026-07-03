# orm-learn

A runnable Spring Boot demo covering two objectives:

1. **Query Methods** (Spring Data JPA) — searching by containing text, sorting,
   filtering by starting text, fetching between dates, greater-than
   comparisons, and "top N" results.
2. **O/R Mapping** — `@ManyToOne`, `@JoinColumn`, `@OneToMany`, `@ManyToMany`,
   `@JoinTable`, `mappedBy`, and `FetchType.EAGER` / `LAZY`.

## Project layout

```
src/main/java/com/cognizant/ormlearn/
  OrmLearnApplication.java        # runs every demo scenario on startup
  model/
    Country.java, Stock.java      # Query Methods demo entities
    Employee.java, Department.java, Skill.java   # O/R Mapping demo entities
  repository/
    CountryRepository.java        # Query Methods: containing / sorting / startingWith
    StockRepository.java          # Query Methods: between dates / greaterThan / top N
    EmployeeRepository.java, DepartmentRepository.java, SkillRepository.java
  service/
    EmployeeService.java, DepartmentService.java, SkillService.java
src/main/resources/
  application.properties          # H2 by default, MySQL instructions included
  schema.sql                      # table definitions (H2), auto-run on startup
  data.sql                        # sample data, auto-run on startup
sql/
  mysql-schema.sql                # equivalent schema if you switch to MySQL
```

## Running it

The project uses an in-memory H2 database by default, so it runs with no
external setup:

```
mvn spring-boot:run
```

On startup, `OrmLearnApplication` exercises every scenario and logs the
results (DEBUG logging is enabled for `com.cognizant.ormlearn`).

To point it at MySQL instead (matching the original hands-on exercise),
edit `src/main/resources/application.properties` as described in the
comments at the bottom of that file, and load `sql/mysql-schema.sql` plus
your own data into a MySQL `ormlearn` schema first.

## 1. Query Methods

### CountryRepository
| Scenario | Method |
|---|---|
| Search box: countries whose name contains typed text | `findByNameContaining(String text)` |
| Same, sorted ascending | `findByNameContainingOrderByNameAsc(String text)` |
| Alphabet index: countries starting with a letter | `findByNameStartingWith(String letter)` |

### StockRepository
| Scenario | Method |
|---|---|
| Stock for a code within a date range (e.g. FB, Sept 2019) | `findByCodeAndDateBetween(String code, LocalDate start, LocalDate end)` |
| Stock for a code with close price greater than a value | `findByCodeAndCloseGreaterThan(String code, BigDecimal price)` |
| Top 3 highest-volume trading days | `findTop3ByOrderByVolumeDesc()` |
| Top 3 lowest-closing days for a code | `findTop3ByCodeOrderByCloseAsc(String code)` |

All of the above are declared as interface methods only — Spring Data JPA
derives the query from the method name, no implementation required.

## 2. O/R Mapping

- **Employee → Department**: `@ManyToOne` + `@JoinColumn(name = "em_dp_id")`
  on `Employee`. Default fetch is `EAGER`, so loading an employee also loads
  its department in the same query (see the join in the generated SQL,
  visible in the logs).
- **Department → Employee**: `@OneToMany(mappedBy = "department", fetch = FetchType.EAGER)`
  on `Department`. `mappedBy` marks `Department` as the inverse (non-owning)
  side of the relationship. Fetch type is switched from the JPA default of
  `LAZY` to `EAGER` to demonstrate the difference — try changing it back to
  `LAZY` and observe the `LazyInitializationException` if you access
  `getEmployeeList()` outside a transaction.
- **Employee ↔ Skill**: `@ManyToMany` on `Employee` (owning side) with
  `@JoinTable(name = "employee_skill", joinColumns = ..., inverseJoinColumns = ...)`,
  and `@ManyToMany(mappedBy = "skillList")` on `Skill` (inverse side).

`OrmLearnApplication.run()` demonstrates:
- `testGetEmployee()` — fetch an employee with department and skills populated
- `testGetDepartment()` — fetch a department with its employee list populated
- `testAddEmployee()` — create and persist a new employee linked to a department
- `testUpdateEmployee()` — reassign an employee's department and persist the change
- `testAddSkillToEmployee()` — add a skill to an employee's many-to-many skill list
