package nz.h4t.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class was forked from a version developed by Anameg Consulting.
 * Copyright Anameg Consulting 2006-2019, All Rights Reserved
 */
public class O {
    private static final Logger log = LoggerFactory.getLogger(O.class);
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * Creates a deep copy of the provided object using JSON serialization and deserialization.
     *
     * @param <T> the type of the object to be cloned
     * @param obj the object to clone
     * @return a deep copy of the provided object, or null if cloning fails due to an exception
     */
    public static <T> T clone(T obj) {
        try {
            return (T) objectMapper.readValue(objectMapper.writeValueAsString(obj), obj.getClass());
        } catch (Exception e) {
            log.error("Problem cloning class", e);
            return null;
        }
    }

    public static <T> T coalesce(T... items) {
        for (var i : items) {
            if (i != null) {
                return i;
            }
        }
        throw new NullPointerException();
    }

    public static <T> T coalesce(T a, T b) {
        return a == null ? checkNotNull(b) : a;
    }

    public static <T> T coalesce(T a, T b, T c) {
        return a != null ? a : (b != null ? b : checkNotNull(c));
    }

    //
    // Internal Methods...
    //

    private static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

}
