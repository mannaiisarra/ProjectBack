package com.spring.pfe.models;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomSerializerFormation extends StdSerializer<Formation> {

    public CustomSerializerFormation() {
        this(null);
    }

    public CustomSerializerFormation(Class<Formation> t) {
        super(t);
    }

    @Override
    public void serialize(
            Formation item,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {
        Formation theme=new Formation();
        theme.setId(item.getId());
        theme.setId(item.getId());
        theme.setTitre(item.getTitre());
        theme.setDescription(item.getDescription());
        generator.writeObject(theme);
    }
}