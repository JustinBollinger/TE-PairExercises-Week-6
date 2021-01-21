package com.techelevator.projects.model.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.interfaces.EmployeeDAO;

public class JDBCEmployeeDAO implements EmployeeDAO
{

	private JdbcTemplate jdbcTemplate;

	public JDBCEmployeeDAO(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Employee> getAllEmployees()
	{
// 				***code here***
		// 1. container to hold departments
		List<Employee> employees = new ArrayList<Employee>();
		
		// query written out
		String query = "SELECT employee_id\r\n" + 
				"        , department_id\r\n" + 
				"        , first_name\r\n" + 
				"        , last_name\r\n" + 
				"        , birth_date\r\n" + 
				"        , gender\r\n" + 
				"        , hire_date\r\n" + 
				"FROM employee;";
		
		// execute the query
		SqlRowSet rows = jdbcTemplate.queryForRowSet(query);
		
		while (rows.next())
		{	
			Long employeeId = rows.getLong("employee_id");
			Long departmentId = rows.getLong("department_id");
			String firstName = rows.getString("first_name");
			String lastName = rows.getString("last_name");
			LocalDate birthDate = rows.getDate("birth_date").toLocalDate();
			String gender = rows.getString("gender");
			LocalDate hireDate = rows.getDate("hire_date").toLocalDate();
			
			Employee employee = new Employee();
			
			employee.setId(employeeId);
			employee.setDepartmentId(departmentId);
			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setBirthDay(birthDate);
			employee.setGender(gender);
			employee.setHireDate(hireDate);
			
			employees.add(employee);
		}	
		return employees;
	}

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch)
	{
		return new ArrayList<>();
	}

	@Override
	public List<Employee> getEmployeesByDepartmentId(long id)
	{
		return new ArrayList<>();
	}

	@Override
	public List<Employee> getEmployeesWithoutProjects()
	{
		return new ArrayList<>();
	}

	@Override
	public List<Employee> getEmployeesByProjectId(Long projectId)
	{
		return new ArrayList<>();
	}

	@Override
	public void changeEmployeeDepartment(Long employeeId, Long departmentId)
	{

	}

}
