package com.LGR.video_store.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LGR.video_store.entities.Employee;
import com.LGR.video_store.entities.User;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	boolean existsByUserId(Long userId);

	Optional<Employee> findByUser(User user);
}
