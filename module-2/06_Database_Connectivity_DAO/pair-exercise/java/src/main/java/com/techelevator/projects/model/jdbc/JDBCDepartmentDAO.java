package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;
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
	public List<Department> getAllDepartments()
	{
		// ***code here***
		// 1. container to hold departments
		List<Department> departments = new ArrayList<Department>();
		
		// query written out
		String query = "SELECT department_id\r\n" + 
						"        , name\r\n" + 
					   "FROM department;";
		
		// execute the query
		SqlRowSet rows = jdbcTemplate.queryForRowSet(query);
		
		while (rows.next())
		{
			departments.add(mapRowToDepartment(rows));
		}	
		return departments;
	}

	
	@Override
	public List<Department> searchDepartmentsByName(String nameSearch)
	{
		// 1. container to hold departments
		List<Department> departments = new ArrayList<Department>();
		
		// query written out
		String sql = "SELECT department_id\r\n" + 
						"        , name\r\n" + 
			       " FROM department\r\n" +
			       " WHERE name ILIKE ?;"; 
		// allows for a dynamic search based on what the person searches for 
		// AND the '?' protects against a SQL injection attack.
		
		// execute the query
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, nameSearch);
		
		while (rows.next())
		{
			
			departments.add(mapRowToDepartment(rows));
			
		}	
		return departments;
	}

	
	@Override
	public void saveDepartment(Department updatedDepartment)
	{
		String sql = "UPDATE department\r\n" +
						"SET name = ?\r\n" +
						"WHERE department_id = ?;";
		
		jdbcTemplate.update(sql
								, updatedDepartment.getName()
								, updatedDepartment.getId());
	}

	@Override
	public Department createDepartment(Department newDepartment)
	{
		// COME BACK TO THIS ON
		
		Department department = new Department();
//		int id = 0;
		String insertQuery = "INSERT INTO department (department_id, name)\r\n" + 
				 "VALUES (?, ?);";

		jdbcTemplate.update(insertQuery
								, newDepartment.getId()
								, newDepartment.getName());
//		if (id = null)
//		{
//			
//		}
		
		return department;
	}

	@Override
	public Department getDepartmentById(Long id)
	{		
		Department departments = null;
		
		String query = "SELECT department_id\r\n" + 
					   " , name\r\n" + 
					   " FROM department\r\n" + 
					   " WHERE department_id = ?;";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(query, id);
		
		if (rows.next())
		{
			departments = mapRowToDepartment(rows);
		}
		
		return departments;
	}
	
	
	private Department mapRowToDepartment(SqlRowSet rows)
	{
		Long id = rows.getLong("department_id");
		String deptName = rows.getString("name");
		
		Department department = new Department();
		
		department.setId(id);
		department.setName(deptName);
		
		return department;
	}
	
	
}
