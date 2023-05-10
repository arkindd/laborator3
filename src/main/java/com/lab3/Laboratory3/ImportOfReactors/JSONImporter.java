package com.lab3.Laboratory3.ImportOfReactors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class JSONImporter extends Importer {

    @Override
    public ReactorType importFromFile(File file) throws IOException {
        if (Objects.equals(getExtension(file), "json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            ReactorType reactorType = objectMapper.readValue(file, new TypeReference<>() {
            });
            reactorType.setImportMethod("Imported from JSON");
            return reactorType;
        } else return null;
    }
}