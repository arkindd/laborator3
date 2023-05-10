package com.lab3.Laboratory3.repository;

import com.lab3.Laboratory3.domain.Sites;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SitesRepository extends JpaRepository<Sites, Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "ALTER TABLE sites" +
            "   ADD CONSTRAINT FK_sites_countries FOREIGN KEY (place)" +
            "      REFERENCES countries (id)" +
            "      ON DELETE CASCADE" +
            "      ON UPDATE CASCADE")
    void createSitesKeys();
}
