package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes.Name;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.interfaces.DepartmentDAO;

public class JDBCDepartmentDAO implements DepartmentDAO
{

	private JdbcTemplate jdbcTemplate;

	public JDBCDepartmentDAO(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Department> getAllDepartments(ArrayList<Department> name)
	{
		// ***code here***
		// 1. container to hold departments
		List<Department> departments = new ArrayList<Department>();
		
		// query written out
		String query = "SELECT name\r\n" + 
					   "FROM department;";
		
		// execute the query
		SqlRowSet rows = jdbcTemplate.queryForRowSet(query, name);
		
		while (rows.next())
		{
			Department department = mapRowToDepartment(rows);
			
			departments.add(department);
		}
		
		
		return departments;
	}

	@Override
	public List<Department> searchDepartmentsByName(String nameSearch)
	{
		
		return new ArrayList<>();
	}

	@Override
	public void saveDepartment(Department updatedDepartment)
	{

	}

	@Override
	public Department createDepartment(Department newDepartment)
	{
		return null;
	}

	@Override
	public Department getDepartmentById(Long id)
	{
		return null;
	}

}
