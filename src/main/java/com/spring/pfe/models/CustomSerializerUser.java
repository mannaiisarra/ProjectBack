package com.spring.pfe.models;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomSerializerUser extends StdSerializer<User> {

    public CustomSerializerUser() {
        this(null);
    }

    public CustomSerializerUser(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(
            User item,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {
        User theme=new User();
        theme.setId(item.getId());
        theme.setUsername(item.getUsername());
        generator.writeObject(theme);
    }
}