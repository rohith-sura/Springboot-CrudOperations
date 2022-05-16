package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Employee;
import com.example.demo.repository.Repository;

@RestController
public class Controller {
	
	private static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	private Repository repo;
	
	@PostMapping(value = "/insert")
	public String insertData(@RequestBody Employee emp) {
		LOGGER.info("Entered............");
		repo.save(emp);
		//String id = emp.getId().toString();
		return "Employee inserted to the Database!";
	}

	@GetMapping(value = "/{id}")
	public Optional<Employee> retriveById(@PathVariable(name = "id") int id) {
		return repo.findById(id);
	}

	@GetMapping(value = "/allEmployees")
	public List<Employee> retriveAll() {
		return repo.findAll();
	}

	@PutMapping(value = "/updateEmployee/{id}")
	public String updateById(@PathVariable(name = "id") int id, @RequestBody Employee updatedEmp) {
		Employee employee = repo.findById(id).get();
		employee.setFname(updatedEmp.getFname());
		employee.setLname(updatedEmp.getLname());
		employee.setDepartment(updatedEmp.getDepartment());
		repo.save(employee);
		return "Record Updated of an employee with Id: "+ id;
	}

	@DeleteMapping(value = "/deleteById")
	public String deleteById(@RequestParam(value = "id") int id) {
		repo.deleteById(id);
		return "Deleted record of an employee with Id: "+ id;
	}

	@DeleteMapping(value = "/deleteAll")
	public String deleteAll() {
		repo.deleteAll();
		return "Deleted all the records from Database!";
	}

}
