package mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	
}