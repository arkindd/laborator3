package com.lab3.Laboratory3.ImportOfReactors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Objects;
import java.util.stream.Collectors;

public class XMLImporter extends Importer {

    @Override
    public ReactorType importFromFile(File file) throws FileNotFoundException, JAXBException {
        if (Objects.equals(getExtension(file), "xml")) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String body = br.lines().collect(Collectors.joining());
            StringReader reader = new StringReader(body);
            JAXBContext context = JAXBContext.newInstance(ReactorType.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ReactorType reactorType = (ReactorType) unmarshaller.unmarshal(reader);
            reactorType.setImportMethod("Imported from XML");
            return reactorType;
        } else return null;
    }
}