package com.project.ServiceBooking.repositories;

import com.project.ServiceBooking.data.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill,Integer> {

    @Query(value = "SELECT * FROM skills WHERE seller_id_seller = ?1",
            nativeQuery = true)
    List<Skill> findBySeller(Integer sellerId);
}
