package com.spring.pfe.models;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class CustomSerializer extends StdSerializer<Theme> {

    public CustomSerializer() {
        this(null);
    }

    public CustomSerializer(Class<Theme> t) {
        super(t);
    }

    @Override
    public void serialize(
            Theme item,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {
        Theme theme=new Theme();
        theme.setId(item.getId());
        theme.setTheme_titre(item.getTheme_titre());
        generator.writeObject(theme);
    }
}