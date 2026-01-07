package com.LGR.video_store.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LGR.video_store.entities.MovieCopy;

@Repository
public interface MovieCopyRepository extends JpaRepository<MovieCopy, Long>{

	List<MovieCopy> findByActiveTrue();
	
	Optional<MovieCopy> findByIdAndActiveTrue(Long id);
}
