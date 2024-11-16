import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public class Queries extends DatabaseHelper {

    // Query 2: List all employees in 'Human Resources' department
    @Test
    public void executeQuery2() {


        String query = "SELECT employees.emp_no, employees.first_name, employees.last_name, employees.gender, employees.hire_date " +
                "FROM employees " +
                "INNER JOIN dept_emp ON employees.emp_no = dept_emp.emp_no " +
                "INNER JOIN departments ON dept_emp.dept_no = departments.dept_no " +
                "WHERE departments.dept_name = 'Human Resources';";
        List<List<String>> data = getDataListWithHeaders(query);

        // Print data rows (headers are already included in data)
        for (List<String> row : data) {
            System.out.println(String.join("\t", row));
        }
    }


    // Query 7: Employees with salary between 50000 and 100000
    @Test
    public void executeQuery7() {
        String query = "SELECT e.emp_no, e.first_name, e.last_name, e.gender, e.hire_date " +
                "FROM employees e " +
                "INNER JOIN salaries s ON e.emp_no = s.emp_no " +
                "WHERE s.salary BETWEEN 50000 AND 100000;";

        List<List<String>> data = getDataListWithHeaders(query);

        for (List<String> row : data) {
            System.out.println(String.join("\t", row));
        }
    }

    // Query 12: Find the employee with the highest salary
    @Test
    public void executeQuery12() {

        String query = "SELECT e.emp_no, e.first_name, e.last_name, e.gender, s.salary " +
                "FROM employees e " +
                "JOIN salaries s ON e.emp_no = s.emp_no " +
                "ORDER BY s.salary DESC LIMIT 1;";

        List<List<String>> data = getDataListWithHeaders(query);

        for (List<String> row : data) {
            System.out.println(String.join("\t", row));
        }

    }

    // Query 17: Highest average salary employees by department
    @Test
    public void executeQuery17() {
        String query = "SELECT d.dept_name AS department, e.first_name, e.last_name, AVG(s.salary) AS avg_salary " +
                "FROM employees e " +
                "INNER JOIN dept_emp de ON e.emp_no = de.emp_no " +
                "INNER JOIN departments d ON de.dept_no = d.dept_no " +
                "INNER JOIN salaries s ON e.emp_no = s.emp_no " +
                "GROUP BY department " +
                "ORDER BY avg_salary DESC;";

        List<List<String>> data = getDataListWithHeaders(query);

        for (List<String> row : data) {
            System.out.println(String.join("\t", row));

        }
    }

    // Query 22: Count of male and female employees
    @Test
    public void executeQuery22() {

        String query = "SELECT gender, COUNT(*) AS count FROM employees GROUP BY gender;";

        List<List<String>> data = getDataListWithHeaders(query);

        for (List<String> row : data) {
            System.out.println(String.join("\t", row));

        }


    }

    // Query 27: Employees and managers in D005 department
    @Test
    public void executeQuery27() {
        String query = "SELECT e.emp_no, e.first_name, e.last_name, e.birth_date, e.gender, e.hire_date, " +
                "d.dept_name AS department_name, t.title, s.salary " +
                "FROM employees e " +
                "JOIN dept_manager dm ON e.emp_no = dm.emp_no " +
                "JOIN departments d ON dm.dept_no = d.dept_no " +
                "JOIN titles t ON e.emp_no = t.emp_no " +
                "JOIN salaries s ON e.emp_no = s.emp_no " +
                "WHERE d.dept_no = 'D005';";

        List<List<String>> data = getDataListWithHeaders(query);

        for (List<String> row : data) {
            System.out.println(String.join("\t", row));

        }


    }

    // Query 32: Position history for employee with emp. no '10102'
    @Test
    public void executeQuery32() {
        String query = "SELECT emp_no, title, from_date, to_date " +
                "FROM titles " +
                "WHERE emp_no = '10102' ORDER BY from_date;";

        List<List<String>> data = getDataListWithHeaders(query);

        for (List<String> row : data) {
            System.out.println(String.join("\t", row));

        }


    }

    // Query 37: Latest title information for each employee
    @Test
    public void executeQuery37() {
        String query = "SELECT employees.emp_no, employees.first_name, employees.last_name, titles.title " +
                "FROM employees " +
                "JOIN titles ON employees.emp_no = titles.emp_no " +
                "WHERE titles.to_date = (SELECT MAX(to_date) FROM titles WHERE titles.emp_no = employees.emp_no);";

        List<List<String>> data = getDataListWithHeaders(query);

        for (List<String> row : data) {
            System.out.println(String.join("\t", row));

        }

    }
    @Test
    //Task 3: Calculate the average salary of all employees
    public void executeQuery3() {
        String query = "select avg(salary) AS AverageSalary from salaries left join dept_emp ON dept_emp.emp_no=salaries.emp_no;";

        List<List<String>> data = getDataListWithHeaders(query);

        for (List<String> row : data) {
            System.out.println(String.join("\t", row));

        }

    }

    @Test
    // Task 8: Calculate the average salary for each department (by department number or department name)-
    public  void executeQuery8()
    {
        String query = "select avg(salary) AS AverageSalary,dept_emp.dept_no as DepartmentNo from salaries left join dept_emp ON dept_emp.emp_no=salaries.emp_no group by dept_no;";
        List<List<String>> data = getDataListWithHeaders(query);

        for (List<String> row : data) {
            System.out.println(String.join("\t", row));

        }
    }

    @Test
    // Task 13: Find the latest salaries for each employee

    public  void executeQuery13()
    {
        String query = "select emp_no,salary from salaries where to_date like '9999%' order by salary desc limit 100;";
        List<List<String>> data = getDataListWithHeaders(query);

        for (List<String> row : data) {
            System.out.println(String.join("\t", row));

        }


}
    @Test
    // Task 18: List the names, last names, and hire dates in alphabetical order of all employees hired before
    // January 01, 1990.

    public  void executeQuery18()
    {
        String query = "select first_name as FirstName,last_name as LastName,hire_date as HireDate from employees where hire_date<'1990-01-01' limit 10;";
        List<List<String>> data = getDataListWithHeaders(query);

        for (List<String> row : data) {
            System.out.println(String.join("\t", row));

        }

}

//Task 23:List the number of employees in each department
    @Test
    public  void executeQuery23()
    {
        String query = "select count(emp_no) as ÇalışanSayısı,departments.dept_name as Departman from dept_emp left join departments ON departments.dept_no=dept_emp.dept_no group by dept_emp.dept_no limit 10;";
        List<List<String>> data = getDataListWithHeaders(query);

        for (List<String> row : data) {
            System.out.println(String.join("\t", row));

        }
}
}