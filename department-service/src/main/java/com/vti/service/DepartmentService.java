package com.vti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.entity.Department;
import com.vti.form.DepartmentFilterForm;
import com.vti.repository.IDepartmentRepository;
import com.vti.specification.department.DepartmentSpecification;

import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	private IDepartmentRepository repository;

	public Page<Department> getAllDepartments(Pageable pageable, String search, DepartmentFilterForm filterForm) {

		Specification<Department> where = DepartmentSpecification.buildWhere(search, filterForm);
		return repository.findAll(where, pageable);
	}

	@Override
	public Department getDepartmentById(int id) {
		Optional<Department> departmentOpt = repository.findById(id);
        return departmentOpt.orElseGet(() -> repository.findById(id).isPresent() ? repository.findById(id).get() : null);
    }

}
