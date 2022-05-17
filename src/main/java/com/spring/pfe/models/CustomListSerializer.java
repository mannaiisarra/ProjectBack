package com.spring.pfe.models;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomListSerializer extends StdSerializer<List<Etape>> {

    public CustomListSerializer() {
        this(null);
    }

    public CustomListSerializer(Class<List<Etape>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<Etape> items,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {

        List<Etape> ids = new ArrayList<>();
        for (Etape item : items) {
            ids.add(item);
        }
        generator.writeObject(ids);
    }
}