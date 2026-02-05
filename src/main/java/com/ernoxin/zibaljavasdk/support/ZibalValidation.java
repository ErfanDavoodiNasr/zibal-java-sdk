package com.ernoxin.zibaljavasdk.support;

import com.ernoxin.zibaljavasdk.exception.ZibalValidationException;
import lombok.experimental.UtilityClass;

import java.net.URI;
import java.util.Locale;
import java.util.regex.Pattern;

@UtilityClass
public class ZibalValidation {
    private static final Pattern CARD_PATTERN = Pattern.compile("^\\d{16}$");
    private static final Pattern NATIONAL_CODE_PATTERN = Pattern.compile("^\\d{10}$");
    private static final Pattern IBAN_PATTERN = Pattern.compile("^IR\\d{24}$");

    public static void requireNonBlank(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new ZibalValidationException(field + " is required");
        }
    }

    public static void requirePositive(long value, String field) {
        if (value <= 0) {
            throw new ZibalValidationException(field + " must be positive");
        }
    }

    public static void requireMin(long value, long min, String field) {
        if (value < min) {
            throw new ZibalValidationException(field + " must be at least " + min);
        }
    }

    public static void requireHttpUri(URI uri, String field) {
        if (uri == null) {
            throw new ZibalValidationException(field + " is required");
        }
        if (!uri.isAbsolute() || uri.getScheme() == null) {
            throw new ZibalValidationException(field + " must be an absolute URL");
        }
        String scheme = uri.getScheme().toLowerCase(Locale.ROOT);
        if (!scheme.equals("http") && !scheme.equals("https")) {
            throw new ZibalValidationException(field + " must use http or https");
        }
    }

    public static void requireHttpsUri(URI uri, String field) {
        if (uri == null) {
            throw new ZibalValidationException(field + " is required");
        }
        if (!uri.isAbsolute() || uri.getScheme() == null) {
            throw new ZibalValidationException(field + " must be an absolute URL");
        }
        String scheme = uri.getScheme().toLowerCase(Locale.ROOT);
        if (!scheme.equals("https")) {
            throw new ZibalValidationException(field + " must use https");
        }
    }

    public static void requireCardNumber(String value, String field) {
        requireNonBlank(value, field);
        String trimmed = value.trim();
        if (!CARD_PATTERN.matcher(trimmed).matches()) {
            throw new ZibalValidationException(field + " must be 16 digits");
        }
    }

    public static void requireNationalCode(String value) {
        requireNonBlank(value, "nationalCode");
        String trimmed = value.trim();
        if (!NATIONAL_CODE_PATTERN.matcher(trimmed).matches()) {
            throw new ZibalValidationException("nationalCode must be 10 digits");
        }
    }

    public static void requireIban(String value, String field) {
        requireNonBlank(value, field);
        String normalized = value.trim().toUpperCase(Locale.ROOT);
        if (!IBAN_PATTERN.matcher(normalized).matches()) {
            throw new ZibalValidationException(field + " must start with IR and be 26 characters");
        }
    }

    public static void requireMode(Integer value, String field) {
        if (value == null) {
            return;
        }
        if (value != 0 && value != 1) {
            throw new ZibalValidationException(field + " must be 0 or 1");
        }
    }

    public static URI normalizeBaseUrl(URI uri) {
        String value = uri.toString();
        while (value.endsWith("/")) {
            value = value.substring(0, value.length() - 1);
        }
        return URI.create(value);
    }
}
