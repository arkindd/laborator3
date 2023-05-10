package com.lab3.Laboratory3.repository;

import com.lab3.Laboratory3.domain.Companies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompaniesRepository extends JpaRepository<Companies, Integer> {
}
