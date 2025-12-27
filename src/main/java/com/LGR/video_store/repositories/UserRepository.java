package com.LGR.video_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LGR.video_store.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
