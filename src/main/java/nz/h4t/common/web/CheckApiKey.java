package nz.h4t.common.web;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CheckApiKey {
    public static void check(String expectedApiKey, String apiKey) {
        if (expectedApiKey == null || expectedApiKey.isBlank() || apiKey == null) {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
        if (!constantTimeEquals(expectedApiKey, apiKey)) {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
    }

    private static boolean constantTimeEquals(String expected, String provided) {
        // Hash both to a fixed 32 bytes first, so neither the key's length nor its content
        // leaks through comparison timing, then compare with the constant-time primitive.
        return MessageDigest.isEqual(sha256(expected), sha256(provided));
    }

    private static byte[] sha256(String value) {
        try {
            return MessageDigest.getInstance("SHA-256").digest(value.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 unavailable", e);
        }
    }
}