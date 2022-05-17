package com.spring.pfe.models;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomListtSerializerTheme extends StdSerializer<List<Theme>> {

    public CustomListtSerializerTheme() {
        this(null);
    }

    public CustomListtSerializerTheme(Class<List<Theme>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<Theme> items,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {

        List<Theme> ids = new ArrayList<>();
        for (Theme item : items) {
            ids.add(item);
        }
        generator.writeObject(ids);
    }
}