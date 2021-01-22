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
			employees.add(mapRowToEmployee(rows));
		}	
		return employees;
	}

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch)
	{
		List<Employee> employees = new ArrayList<Employee>();
		
		String query = "SELECT employee_id\r\n" + 
				"        , department_id\r\n" + 
				"        , first_name\r\n" + 
				"        , last_name\r\n" + 
				"        , birth_date\r\n" + 
				"        , gender\r\n" + 
				"        , hire_date\r\n" + 
				" FROM employee\r\n" + 
				" WHERE first_name ILIKE ? AND last_name ILIKE ?;";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(query, firstNameSearch, lastNameSearch);
		
		while (rows.next())
		{		
			employees.add(mapRowToEmployee(rows));	
		}
		return employees;
	}

	@Override
	public List<Employee> getEmployeesByDepartmentId(long id)
	{
		
		// not sure if this one works or not
		List<Employee> employees = new ArrayList<Employee>();
		
		String query = "SELECT employee_id\r\n" + 
					   " , department_id\r\n" + 
					   " , first_name\r\n" + 
					   " , last_name\r\n" + 
					   " , birth_date\r\n" + 
					   " , gender\r\n" + 
					   " , hire_date\r\n" + 
					   " FROM employee\r\n" + 
					   " WHERE department_id = ?;";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(query, id);
		
		while (rows.next())
		{			
			employees.add(mapRowToEmployee(rows));	
		}
		return employees;
	}

	
	@Override
	public List<Employee> getEmployeesWithoutProjects()
	{
//		STILL NEED TO FIGURE OUT THE QUERY
		List<Employee> employees = new ArrayList<Employee>();
		
		String query = "";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(query);
		
		while (rows.next())
		{
			employees.add(mapRowToEmployee(rows));
		}
		
		return employees;
	}

	@Override
	public List<Employee> getEmployeesByProjectId(Long projectId)
	{
		// STILL NEED THE QUERY
		
		List<Employee> employees = new ArrayList<Employee>();
		
		String query = "";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(query, projectId);
		
		while (rows.next())
		{
			
			employees.add(mapRowToEmployee(rows));
			
		}
		
		return employees;
	}

	
	@Override
	public void changeEmployeeDepartment(Long employeeId, Long departmentId)
	{

	}

	// Created a function in order to avoid having to
	// type out this list for each above function.
	private Employee mapRowToEmployee(SqlRowSet rows)
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
		
		return employee;
	}
}
