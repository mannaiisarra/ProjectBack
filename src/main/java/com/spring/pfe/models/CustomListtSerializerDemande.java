package com.spring.pfe.models;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomListtSerializerDemande  extends StdSerializer<List<Demande>> {

    public CustomListtSerializerDemande() {
        this(null);
    }

    public CustomListtSerializerDemande(Class<List<Demande>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<Demande> items,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {

        List<Demande> ids = new ArrayList<>();
        for (Demande item : items) {
            ids.add(item);
        }
        generator.writeObject(ids);
    }
}