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
			Long id = rows.getLong("department_id");
			String deptName = rows.getString("name");
			
			
//			Department department = mapRowToDepartment(rows);
			Department department = new Department();
			
			department.setId(id);
			department.setName(deptName);
			
			departments.add(department);
		}	
		return departments;
	}

	@Override
	public List<Department> searchDepartmentsByName(String nameSearch)
	{
		List<Department> departments = new ArrayList<Department>();
		
		// query written out
		String query = "SELECT department_id\r\n" + 
						"        , name\r\n" + 
					   "FROM department;";
		
		// execute the query
		SqlRowSet rows = jdbcTemplate.queryForRowSet(query);
		
		while (rows.next())
		{
			String deptName = rows.getString("name");

			Department department = new Department();
			
			if (deptName.toString() == nameSearch.toString()) 
			{
				department.setName(deptName);
				departments.add(department);
			}
								
		}
		return departments;
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
	
//	private Department mapRowToDepartment(SqlRowSet rows)
//	{
//
//		
//		Department theDepartment;
//		theDepartment = new Department();
//		//theDepartment.setId(rows.getLong("id"));
//		theDepartment.setName(rows.getString("name"));
//		return theDepartment;	
//	}
	
	
}
