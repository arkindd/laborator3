package com.lab3.Laboratory3.ImportOfReactors;

import com.lab3.Laboratory3.domain.Units;
import com.lab3.Laboratory3.repository.UnitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;

@Component
public class BurnUpImportChain {

    static UnitsRepository unitsRepository;

    @Autowired
    public BurnUpImportChain(UnitsRepository unitsRepository) {
        this.unitsRepository = unitsRepository;
    }

    static final XMLImporter xmlImporter = new XMLImporter();
    static final YAMLImporter yamlImporter = new YAMLImporter();
    static final JSONImporter jsonImporter = new JSONImporter();

    public static void fillBurnUpAndVolumeOfConsume(File file) throws JAXBException, IOException {
        xmlImporter.setNextImporter(yamlImporter);
        yamlImporter.setNextImporter(jsonImporter);
        jsonImporter.setNextImporter(null);
        ReactorType reactorType = xmlImporter.importManager(file);
        ArrayList<Reactor> reactors = (ArrayList<Reactor>) reactorType.getReactorList();
        ArrayList<Units> units = (ArrayList<Units>) unitsRepository.findAll();
        for (Units unit : units) {
            for (Reactor reactor : reactors) {
                if (reactor.getType().equals(unit.getUnitClass())) {
                    unit.setBurnup((int) reactor.getBurnup());
                    unitsRepository.save(unit);
                    break;
                }
            }
            if (unit.getBurnup()==0){
                throw new IOException();
            }
            double volumeOfYearConsume = ((365*unit.getThermalCapacity()/(1000*unit.getBurnup()))*unit.getLoadFactor()/100);
            unit.setVolumeOfYearConsume(volumeOfYearConsume);
            unitsRepository.save(unit);
        }
    }
}