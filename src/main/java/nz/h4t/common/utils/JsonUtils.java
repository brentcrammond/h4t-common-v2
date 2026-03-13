package nz.h4t.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * The JsonUtils class provides utility methods for converting JSON to objects and objects to JSON using the Jackson ObjectMapper.
 */
public class JsonUtils {
    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * Converts a JSON string to an object of the specified class type.
     *
     * @param <T>  The type of the object to convert the JSON string to.
     * @param clz  The class type to convert the JSON string to.
     * @param json The JSON string to be converted to the specified object type.
     * @return An Optional containing the converted object if successful, or an empty Optional if the conversion fails.
     */
    public static <T> Optional<T> fromJson(Class<T> clz, String json) {
        try {
            return Optional.of(objectMapper.readValue(json, clz));
        } catch (JsonProcessingException ex) {
            log.error("Problem converting JSON to Object", ex);
            return Optional.empty();
        }
    }

    /**
     * Converts an object to its JSON string representation.
     *
     * @param obj The object to be converted to JSON string.
     * @return The JSON string representation of the object, or an empty string if conversion fails.
     */
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            log.error("Problem converting Object to JSON", ex);
            return "";
        }
    }
}
