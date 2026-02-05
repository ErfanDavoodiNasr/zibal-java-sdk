package com.ernoxin.zibaljavasdk.platform.support;

import com.ernoxin.zibaljavasdk.platform.exception.PlatformValidationException;
import com.ernoxin.zibaljavasdk.support.ZibalValidation;
import lombok.experimental.UtilityClass;

import java.net.URI;
import java.util.Locale;
import java.util.regex.Pattern;

@UtilityClass
public class ZibalPlatformValidation {
    private static final Pattern IBAN_PATTERN = Pattern.compile("^IR\\d{24}$");
    private static final Pattern CARD_PATTERN = Pattern.compile("^\\d{16}$");

    public static void requireNonBlank(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new PlatformValidationException(field + " is required");
        }
    }

    public static void requirePositive(long value, String field) {
        if (value <= 0) {
            throw new PlatformValidationException(field + " must be positive");
        }
    }

    public static void requirePositive(Integer value, String field) {
        if (value == null) {
            return;
        }
        if (value <= 0) {
            throw new PlatformValidationException(field + " must be positive");
        }
    }

    public static void requireHttpsUri(URI uri, String field) {
        if (uri == null) {
            throw new PlatformValidationException(field + " is required");
        }
        if (!uri.isAbsolute() || uri.getScheme() == null) {
            throw new PlatformValidationException(field + " must be an absolute URL");
        }
        String scheme = uri.getScheme().toLowerCase(Locale.ROOT);
        if (!scheme.equals("https")) {
            throw new PlatformValidationException(field + " must use https");
        }
    }

    public static void requireHttpUri(URI uri, String field) {
        if (uri == null) {
            throw new PlatformValidationException(field + " is required");
        }
        if (!uri.isAbsolute() || uri.getScheme() == null) {
            throw new PlatformValidationException(field + " must be an absolute URL");
        }
        String scheme = uri.getScheme().toLowerCase(Locale.ROOT);
        if (!scheme.equals("http") && !scheme.equals("https")) {
            throw new PlatformValidationException(field + " must use http or https");
        }
    }

    public static void requireHttpUrl(String value, String field) {
        requireNonBlank(value, field);
        URI uri;
        try {
            uri = URI.create(value.trim());
        } catch (IllegalArgumentException ex) {
            throw new PlatformValidationException(field + " must be a valid URL");
        }
        requireHttpUri(uri, field);
    }

    public static void requireIban(String value, String field) {
        requireNonBlank(value, field);
        String normalized = value.trim().toUpperCase(Locale.ROOT);
        if (!IBAN_PATTERN.matcher(normalized).matches()) {
            throw new PlatformValidationException(field + " must start with IR and be 26 characters");
        }
    }

    public static void requireCardNumber(String value, String field) {
        requireNonBlank(value, field);
        String trimmed = value.trim();
        if (!CARD_PATTERN.matcher(trimmed).matches()) {
            throw new PlatformValidationException(field + " must be 16 digits");
        }
    }

    public static URI normalizeBaseUrl(URI uri) {
        return ZibalValidation.normalizeBaseUrl(uri);
    }
}
