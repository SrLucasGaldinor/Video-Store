package com.LGR.video_store.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LGR.video_store.entities.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

	List<Genre> findByActiveTrue();
	
	Optional<Genre> findByIdAndActiveTrue(Long id);
}
