package com.LGR.video_store.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LGR.video_store.entities.Employee;
import com.LGR.video_store.entities.User;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	List<Employee> findByActiveTrue();
	
	Optional<Employee> findByIdAndActiveTrue(Long id);
	
	Optional<Employee> findByUser(User user);
	
	Optional<Employee> findByUserAndActiveTrue(User user);
	
	boolean existsByUserIdAndActiveTrue(Long userId);

}
