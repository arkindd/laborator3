package com.lab3.Laboratory3.repository;

import com.lab3.Laboratory3.domain.Units;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UnitsRepository extends JpaRepository<Units, Integer> {

    @Query(nativeQuery = true, value = "SELECT companies_name, sum(units.volume_of_year_consume)" +
            "FROM units INNER JOIN companies ON units.operator=companies.id GROUP BY companies_name")
    ArrayList<Object[]> calculateForCompanies();

    @Query(nativeQuery = true, value = "SELECT country_name, sum(units.volume_of_year_consume)" +
            "FROM units INNER JOIN sites ON units.site=sites.id INNER JOIN countries " +
            "ON sites.place=countries.id GROUP BY country_name")
    ArrayList<Object[]> calculateForCountries();

    @Query(nativeQuery = true, value = "SELECT regions.region_name, sum(units.volume_of_year_consume)" +
            "FROM units INNER JOIN sites ON units.site=sites.id INNER JOIN countries ON " +
            "sites.place=countries.id INNER JOIN regions ON countries.region_id=regions.id GROUP BY regions.region_name")
    ArrayList<Object[]> calculateForRegions();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "ALTER TABLE units" +
            "   ADD CONSTRAINT FK_units_companies FOREIGN KEY (operator)" +
            "      REFERENCES companies (id)" +
            "      ON DELETE CASCADE" +
            "      ON UPDATE CASCADE")
    void createUnitsKeyWithCompanies();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "ALTER TABLE units" +
            "   ADD CONSTRAINT FK_units_sites FOREIGN KEY (site)" +
            "      REFERENCES sites (id)" +
            "      ON DELETE CASCADE" +
            "      ON UPDATE CASCADE")
    void createUnitsKeyWithSites();
}
