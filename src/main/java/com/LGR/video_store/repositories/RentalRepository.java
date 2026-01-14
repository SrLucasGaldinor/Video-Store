package com.LGR.video_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LGR.video_store.entities.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long>{

}
