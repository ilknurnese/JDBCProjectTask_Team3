import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class Queries extends DatabaseHelper{



    // Query 2: List all employees in 'Human Resources' department
    @Test
    public void executeQuery2() {
        List<List<String>> data = getDataList("SELECT employees.* " +
                "FROM employees " +
                "INNER JOIN dept_emp ON employees.emp_no = dept_emp.emp_no " +
                "INNER JOIN departments ON dept_emp.dept_no = departments.dept_no " +
                "WHERE departments.dept_name = 'Human Resources';");
        for (List<String> row : data) {
            System.out.println(row);
        }
    }

    // Query 7: Employees with salary between 50000 and 100000
    @Test
    public void executeQuery7() {
        List<List<String>> data = getDataList("SELECT e.* " +
                "FROM employees e " +
                "INNER JOIN salaries s ON e.emp_no = s.emp_no " +
                "WHERE s.salary BETWEEN 50000 AND 100000;");
        for (List<String> row : data) {
            System.out.println(row);
        }
    }

    // Query 12: Find the employee with the highest salary
    @Test
    public void executeQuery12() {
        List<List<String>> data = getDataList("SELECT * FROM employees " +
                "JOIN salaries ON employees.emp_no = salaries.emp_no " +
                "ORDER BY salary DESC " +
                "LIMIT 1;");
        for (List<String> row : data) {
            System.out.println(row);
        }
    }

    // Query 17: Highest average salary employees by department
    @Test
    public void executeQuery17() {
        List<List<String>> data = getDataList("SELECT d.dept_name AS department, e.first_name, e.last_name, AVG(s.salary) AS avg_salary " +
                "FROM employees e " +
                "INNER JOIN dept_emp de ON e.emp_no = de.emp_no " +
                "INNER JOIN departments d ON de.dept_no = d.dept_no " +
                "INNER JOIN salaries s ON e.emp_no = s.emp_no " +
                "GROUP BY department " +
                "ORDER BY avg_salary DESC;");
        for (List<String> row : data) {
            System.out.println(row);
        }
    }

    // Query 22: Count of male and female employees
    @Test
    public void executeQuery22() {
        List<List<String>> data = getDataList("SELECT gender, COUNT(*) AS count FROM employees GROUP BY gender;");
        for (List<String> row : data) {
            System.out.println(row);
        }
    }

    // Query 27: Employees and managers in D005 department
    @Test
    public void executeQuery27() {
        List<List<String>> data = getDataList("SELECT e.emp_no, e.first_name, e.last_name, e.birth_date, e.gender, e.hire_date, " +
                "d.dept_name AS department_name, t.title, s.salary " +
                "FROM employees e " +
                "JOIN dept_manager dm ON e.emp_no = dm.emp_no " +
                "JOIN departments d ON dm.dept_no = d.dept_no " +
                "JOIN titles t ON e.emp_no = t.emp_no " +
                "JOIN salaries s ON e.emp_no = s.emp_no " +
                "WHERE d.dept_no = 'D005';");
        for (List<String> row : data) {
            System.out.println(row);
        }
    }

    // Query 32: Position history for employee with emp. no '10102'
    @Test
    public void executeQuery32() {
        List<List<String>> data = getDataList("SELECT emp_no, title, from_date, to_date " +
                "FROM titles " +
                "WHERE emp_no = '10102' " +
                "ORDER BY from_date;");
        for (List<String> row : data) {
            System.out.println(row);
        }
    }

    // Query 37: Latest title information for each employee
    @Test
    public void executeQuery37() {
        List<List<String>> data = getDataList("SELECT employees.emp_no, employees.first_name, employees.last_name, titles.title " +
                "FROM employees " +
                "JOIN titles ON employees.emp_no = titles.emp_no " +
                "WHERE titles.to_date = (SELECT MAX(to_date) FROM titles WHERE titles.emp_no = employees.emp_no);");
        for (List<String> row : data) {
            System.out.println(row);
        }
    }



}
