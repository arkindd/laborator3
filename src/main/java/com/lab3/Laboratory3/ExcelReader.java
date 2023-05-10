package com.lab3.Laboratory3;

import com.lab3.Laboratory3.domain.*;
import jakarta.transaction.Transactional;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelReader {

    @Transactional
    public static void readWorkbookToDatabase(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);

        XSSFSheet regionSheet = wb.getSheet("regions");//создает объект страницы
        DatabaseFiller.addDataFromRegions(fromRowsToRegion(fromSheetToRows(regionSheet)));//заполняет таблицу бд данными из экселя

        XSSFSheet countriesSheet = wb.getSheet("countries");
        DatabaseFiller.addDataFromCountries(fromRowsToCountries(fromSheetToRows(countriesSheet)));

        XSSFSheet companiesSheet = wb.getSheet("companies");
        DatabaseFiller.addDataFromCompanies(fromRowsToCompanies(fromSheetToRows(companiesSheet)));

        XSSFSheet sitesSheet = wb.getSheet("sites");
        DatabaseFiller.addDataFromSites(fromRowsToSites(fromSheetToRows(sitesSheet)));

        XSSFSheet unitsSheet = wb.getSheet("units");
        DatabaseFiller.addDataFromUnits(fromRowsToUnits(fromSheetToRows(unitsSheet)));

        DatabaseFiller.createCountryKeys();
        DatabaseFiller.createSitesKeys();
        DatabaseFiller.createUnitsKeys();
    }

    //из страницы в строки
    public static ArrayList<XSSFRow> fromSheetToRows(XSSFSheet sheet) {
        ArrayList<XSSFRow> rows = new ArrayList<>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            rows.add(sheet.getRow(i));
        }
        return rows;
    }

    public static ArrayList<Units> fromRowsToUnits(ArrayList<XSSFRow> rows) {
        ArrayList<Units> allDataFromUnits = new ArrayList<>();
        for (XSSFRow row : rows) {
            Units unit = FromExcelToObjectsMapper.fromUnitsRowToUnits(row);
            if (unit.getStatus().startsWith("in operation")) {
                allDataFromUnits.add(unit);
            }
            /*if (unit.getCommercialOperation() != null) {
                if (new Date().after(unit.getCommercialOperation())) {
                    if (unit.getDateShutdown() != null) {
                        if (new Date().before(unit.getDateShutdown())) {
                            allDataFromUnits.add(unit);
                        }
                    }
                }
            }*///рабочая проверка по дате, но которая выдает реакторы с типом, которого нет в файле реакторов
        }
        return allDataFromUnits;
    }

    //из строк в объекты, все эти методы
    public static ArrayList<Region> fromRowsToRegion(ArrayList<XSSFRow> rows) {
        ArrayList<Region> allDataFromRegion = new ArrayList<>();
        for (XSSFRow row : rows) {
            Region region = FromExcelToObjectsMapper.fromRegionRowToRegion(row);
            allDataFromRegion.add(region);
        }
        return allDataFromRegion;
    }

    public static ArrayList<Countries> fromRowsToCountries(ArrayList<XSSFRow> rows) {
        ArrayList<Countries> allDataFromCountries = new ArrayList<>();
        for (XSSFRow row : rows) {
            Countries country = FromExcelToObjectsMapper.fromCountriesRowToCountries(row);
            allDataFromCountries.add(country);
        }
        return allDataFromCountries;
    }

    public static ArrayList<Companies> fromRowsToCompanies(ArrayList<XSSFRow> rows) {
        ArrayList<Companies> allDataFromCompanies = new ArrayList<>();
        for (XSSFRow row : rows) {
            Companies companies = FromExcelToObjectsMapper.fromCompaniesRowToCompanies(row);
            allDataFromCompanies.add(companies);
        }
        return allDataFromCompanies;
    }

    public static ArrayList<Sites> fromRowsToSites(ArrayList<XSSFRow> rows) {
        ArrayList<Sites> allDataFromSites = new ArrayList<>();
        for (XSSFRow row : rows) {
            Sites sites = FromExcelToObjectsMapper.fromSitesRowToSites(row);
            allDataFromSites.add(sites);
        }
        return allDataFromSites;
    }

}
