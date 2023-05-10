package com.lab3.Laboratory3;

import com.lab3.Laboratory3.domain.*;
import com.lab3.Laboratory3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

//позволяет спрингу создавать объекты этого класса
@Component
public class DatabaseFiller {

    static UnitsRepository unitsRepository;
    static RegionRepository regionRepository;
    static CountryRepository countryRepository;
    static CompaniesRepository companyRepository;
    static SitesRepository sitesRepository;

    @Autowired
    public DatabaseFiller(UnitsRepository unitsRepository,
                          RegionRepository regionRepository,
                          CountryRepository countryRepository,
                          CompaniesRepository companyRepository,
                          SitesRepository sitesRepository) {
        this.unitsRepository = unitsRepository;
        this.regionRepository = regionRepository;
        this.countryRepository = countryRepository;
        this.companyRepository = companyRepository;
        this.sitesRepository = sitesRepository;
    }

    public static void addDataFromRegions(ArrayList<Region> allDataFromRegion) {
        regionRepository.saveAll(allDataFromRegion);
    }

    public static ArrayList<Object[]> resultForRegions() {
        return unitsRepository.calculateForRegions();
    }

    public static void addDataFromUnits(ArrayList<Units> allDataFromUnits) {
        unitsRepository.saveAll(allDataFromUnits);
    }

    public static void createUnitsKeys() {
        unitsRepository.createUnitsKeyWithCompanies();
        unitsRepository.createUnitsKeyWithSites();
    }

    public static void addDataFromCountries(ArrayList<Countries> allDataFromCountries) {
        countryRepository.saveAll(allDataFromCountries);
    }

    public static void createCountryKeys() {
        countryRepository.createCountryKeys();
    }

    public static ArrayList<Object[]> resultForCountries() {
        return unitsRepository.calculateForCountries();
    }

    public static void addDataFromCompanies(ArrayList<Companies> allDataFromCompanies) {
        companyRepository.saveAll(allDataFromCompanies);
    }

    public static ArrayList<Object[]> resultForCompanies() {
        return unitsRepository.calculateForCompanies();
    }

    public static void addDataFromSites(ArrayList<Sites> allDataFromSites) {
        sitesRepository.saveAll(allDataFromSites);
    }

    public static void createSitesKeys() {
        sitesRepository.createSitesKeys();
    }
}