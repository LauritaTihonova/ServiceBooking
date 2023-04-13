package com.project.ServiceBooking.repositories;

import com.project.ServiceBooking.data.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language,Integer> {

    @Query(value = "SELECT * FROM languages WHERE users_id_users = ?1",
    nativeQuery = true)
    List<Language> findByUser(Integer userId);
}
