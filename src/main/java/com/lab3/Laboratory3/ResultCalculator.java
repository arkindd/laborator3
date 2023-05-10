package com.lab3.Laboratory3;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;

@Component
public class ResultCalculator {

    public static ArrayList<ResultRow> calculateResultForCompanies() {
        ArrayList<ResultRow> results = new ArrayList<>();
        for (Object[] unit : DatabaseFiller.resultForCompanies()){
            ResultRow resultRow = new ResultRow();
            resultRow.setCategory(unit[0].toString());
            resultRow.setVolumeOfConsume(new DecimalFormat("#.##").format(unit[1]));
            results.add(resultRow);
        }
        return results;
    }

    public static ArrayList<ResultRow> calculateResultForCountries() {
        ArrayList<ResultRow> results = new ArrayList<>();
        for (Object[] unit : DatabaseFiller.resultForCountries()){
            ResultRow resultRow = new ResultRow();
            resultRow.setCategory(unit[0].toString());
            resultRow.setVolumeOfConsume(new DecimalFormat("#.##").format(unit[1]));
            results.add(resultRow);
        }
        return results;
    }

    public static ArrayList<ResultRow> calculateResultForRegions() {
        ArrayList<ResultRow> results = new ArrayList<>();
        for (Object[] unit : DatabaseFiller.resultForRegions()){
            ResultRow resultRow = new ResultRow();
            resultRow.setCategory(unit[0].toString());
            resultRow.setVolumeOfConsume(new DecimalFormat("#.##").format(unit[1]));
            results.add(resultRow);
        }
        return results;
    }
}