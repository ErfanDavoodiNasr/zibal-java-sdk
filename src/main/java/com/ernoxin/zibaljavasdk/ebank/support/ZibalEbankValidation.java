package com.ernoxin.zibaljavasdk.ebank.support;

import com.ernoxin.zibaljavasdk.ebank.exception.EbankValidationException;
import lombok.experimental.UtilityClass;

import java.net.URI;
import java.util.Locale;
import java.util.regex.Pattern;

@UtilityClass
public class ZibalEbankValidation {
    private static final Pattern IBAN_PATTERN = Pattern.compile("^IR\\d{24}$");

    public static void requireNonBlank(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new EbankValidationException(field + " is required");
        }
    }

    public static void requirePositive(long value, String field) {
        if (value <= 0) {
            throw new EbankValidationException(field + " must be positive");
        }
    }

    public static void requireMin(long value, long min, String field) {
        if (value < min) {
            throw new EbankValidationException(field + " must be at least " + min);
        }
    }

    public static void requirePositive(Integer value, String field) {
        if (value == null) {
            return;
        }
        if (value <= 0) {
            throw new EbankValidationException(field + " must be positive");
        }
    }

    public static void requireHttpsUri(URI uri, String field) {
        if (uri == null) {
            throw new EbankValidationException(field + " is required");
        }
        if (!uri.isAbsolute() || uri.getScheme() == null) {
            throw new EbankValidationException(field + " must be an absolute URL");
        }
        String scheme = uri.getScheme().toLowerCase(Locale.ROOT);
        if (!scheme.equals("https")) {
            throw new EbankValidationException(field + " must use https");
        }
    }

    public static void requireIban(String value, String field) {
        requireNonBlank(value, field);
        String normalized = value.trim().toUpperCase(Locale.ROOT);
        if (!IBAN_PATTERN.matcher(normalized).matches()) {
            throw new EbankValidationException(field + " must start with IR and be 26 characters");
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
