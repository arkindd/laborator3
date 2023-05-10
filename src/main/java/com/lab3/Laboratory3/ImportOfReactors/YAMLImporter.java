package com.lab3.Laboratory3.ImportOfReactors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class YAMLImporter extends Importer {

    @Override
    public ReactorType importFromFile(File file) throws IOException {
        if (Objects.equals(getExtension(file), "yaml")) {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            ReactorType reactorType = objectMapper.readValue(file, new TypeReference<>() {
            });
            reactorType.setImportMethod("Imported from YAML");
            return reactorType;
        } else return null;
    }
}
