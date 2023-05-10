package com.lab3.Laboratory3.ImportOfReactors;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

public abstract class Importer {

    private Importer nextImporter;

    public void setNextImporter(Importer importer){
        this.nextImporter=importer;
    }

    public ReactorType importManager(File file) throws IOException, JAXBException {

        ReactorType reactorType = this.importFromFile(file);
        try {
            while(reactorType==null){
                reactorType = this.nextImporter.importManager(file);
            }
        } catch (NullPointerException e){
            System.out.println("Wrong file type:" +e.getMessage());
        }
        return reactorType;
    }

    public static String getExtension(File file) {
        String extension = "";
        int dotIndex = file.getName().lastIndexOf(".");
        if (dotIndex > 0) {
            extension = file.getName().substring(dotIndex + 1);
        }
        return extension;
    }

    public abstract ReactorType importFromFile(File file) throws IOException, JAXBException;
}