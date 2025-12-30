package com.LGR.video_store.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LGR.video_store.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByActiveTrue();

	Optional<User> findByIdAndActiveTrue(Long id);
}
