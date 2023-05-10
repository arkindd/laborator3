package com.lab3.Laboratory3;

import com.lab3.Laboratory3.domain.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class FromExcelToObjectsMapper {

    public static Units fromUnitsRowToUnits(XSSFRow row){
        Units units = new Units();
        units.setId((int) row.getCell(0).getNumericCellValue());
        units.setCode(row.getCell(1).getStringCellValue());
        units.setUnitName(row.getCell(2).getStringCellValue());
        if (row.getCell(3).getNumericCellValue()==0){
            units.setSite(161);
        } else units.setSite((int) row.getCell(3).getNumericCellValue());
        units.setStatus(row.getCell(4).getStringCellValue());
        units.setType(row.getCell(5).getStringCellValue());
        units.setModel(row.getCell(6).getStringCellValue());
        if (row.getCell(7).getStringCellValue().startsWith("AGR")){
            units.setUnitClass("MAGNOX");
        } else if (row.getCell(7).getStringCellValue().startsWith("CNP")) {
            units.setUnitClass("CPR-1000");
        } else if (row.getCell(7).getStringCellValue().startsWith("PWR")) {
            units.setUnitClass("PWR");
        } else if (row.getCell(7).getStringCellValue().startsWith("VVER")) {
            units.setUnitClass("VVER-1000");
        } else {
            units.setUnitClass(row.getCell(7).getStringCellValue().substring(0, row.getCell(7).getStringCellValue().indexOf(" ")));
        }
        units.setRuDesign(row.getCell(8).getBooleanCellValue());
        units.setOperator((int) row.getCell(9).getNumericCellValue());
        units.setNsssSuplier((int) row.getCell(10).getNumericCellValue());
        units.setThermalCapacity((int) row.getCell(11).getNumericCellValue());
        units.setGrossCapacity((int) row.getCell(12).getNumericCellValue());
        units.setNetCapacity((int) row.getCell(13).getNumericCellValue());
        units.setConstructionStart(row.getCell(14).getDateCellValue());
        units.setCommercialOperation(row.getCell(15).getDateCellValue());
        units.setDateShutdown(row.getCell(16).getDateCellValue());
        units.setEnrichment(row.getCell(17).getNumericCellValue());
        if (row.getCell(18).getNumericCellValue()==0){
            units.setLoadFactor(90);
        } else units.setLoadFactor((int) row.getCell(18).getNumericCellValue());
        return units;
    }

    public static Region fromRegionRowToRegion(XSSFRow row){
        Region region = new Region();
        region.setId((int) row.getCell(0).getNumericCellValue());
        region.setRegionName(row.getCell(1).getStringCellValue());
        return region;
    }

    public static Countries fromCountriesRowToCountries(XSSFRow row){
        Countries country = new Countries();
        country.setId((int) row.getCell(0).getNumericCellValue());
        country.setCountryName(row.getCell(1).getStringCellValue());
        country.setSubregion(row.getCell(2).getStringCellValue());
        country.setRegionName(row.getCell(3).getStringCellValue());
        country.setRegionId((int) row.getCell(4).getNumericCellValue());
        return country;
    }

    public static Companies fromCompaniesRowToCompanies(XSSFRow row){
        Companies companies = new Companies();
        companies.setId((int) row.getCell(0).getNumericCellValue());
        companies.setCompaniesName(row.getCell(1).getStringCellValue());
        companies.setFullName(row.getCell(2).getStringCellValue());
        companies.setCountryId((int) row.getCell(3).getNumericCellValue());
        return companies;
    }

    public static Sites fromSitesRowToSites(XSSFRow row){
        Sites sites = new Sites();
        sites.setId((int) row.getCell(0).getNumericCellValue());
        sites.setNppName(row.getCell(1).getStringCellValue());
        sites.setPlace((int) row.getCell(2).getNumericCellValue());
        sites.setOwnerId((int) row.getCell(3).getNumericCellValue());
        if (row.getCell(4).getCellType() != CellType.STRING) {
            sites.setOperator((int) row.getCell(4).getNumericCellValue());
        }
        if (row.getCell(5).getCellType() != CellType.STRING){
            sites.setBuilder((int) row.getCell(5).getNumericCellValue());
        }
        return sites;
    }
}