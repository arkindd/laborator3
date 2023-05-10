package com.lab3.Laboratory3.repository;

import com.lab3.Laboratory3.domain.Countries;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Countries, Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "ALTER TABLE countries" +
            "   ADD CONSTRAINT FK_countries_regions FOREIGN KEY (region_id)" +
            "      REFERENCES regions (id)" +
            "      ON DELETE CASCADE" +
            "      ON UPDATE CASCADE")
    void createCountryKeys();
}