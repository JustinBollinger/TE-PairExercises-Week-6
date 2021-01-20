package com.techelevator.projects.model.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.projects.model.Department;

public interface DepartmentDAO {

	/**
	 * Get all departments from the datastore.
	 * 
	 * @return all departments as Department objects in a List
	 */
	public List<Department> getAllDepartments(ArrayList<Department> name);

	/**
	 * Get all the departments whose name contains the search string. Remember
	 * to use LIKE or ILIKE to do a fuzzy match on the search.
	 * 
	 * @param nameSearch the search string to look for in the department name
	 * @return all matching departments as Department objects in a List
	 */
	public List<Department> searchDepartmentsByName(String nameSearch);

	/**
	 * Update a department to the datastore. Only called on departments that
	 * are already in the datastore.
	 * 
	 * @param updatedDepartment the department object to update
	 */
	public void saveDepartment(Department updatedDepartment);

	/**
	 * Inserts a new department into the datastore.
	 * 
	 * @param newDepartment the department object to insert
	 * @return the department object with its new id filled in
	 */
	public Department createDepartment(Department newDepartment);

	/**
	 * Get a department from the datastore that belongs to the given id.
	 * 
	 * @param id the department id to get from the datastore
	 * @return a filled out department object
	 */
	public Department getDepartmentById(Long id);
}
