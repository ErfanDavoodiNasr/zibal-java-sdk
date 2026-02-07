package com.ernoxin.zibaljavasdk.support;

import com.ernoxin.zibaljavasdk.exception.ZibalValidationException;
import lombok.experimental.UtilityClass;

import java.net.URI;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Validation helpers used by the standard gateway API implementation.
 */
@UtilityClass
public class ZibalValidation {
    private static final Pattern CARD_PATTERN = Pattern.compile("^\\d{16}$");
    private static final Pattern NATIONAL_CODE_PATTERN = Pattern.compile("^\\d{10}$");
    private static final Pattern IBAN_PATTERN = Pattern.compile("^IR\\d{24}$");

    /**
     * Requires a non-null and non-blank string.
     *
     * @param value candidate value
     * @param field field name used in exception messages
     * @throws ZibalValidationException if the value is null or blank
     */
    public static void requireNonBlank(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new ZibalValidationException(field + " is required");
        }
    }

    /**
     * Requires a strictly positive long value.
     *
     * @param value candidate numeric value
     * @param field field name used in exception messages
     * @throws ZibalValidationException if value is zero or negative
     */
    public static void requirePositive(long value, String field) {
        if (value <= 0) {
            throw new ZibalValidationException(field + " must be positive");
        }
    }

    /**
     * Requires a value greater than or equal to the given minimum.
     *
     * @param value candidate numeric value
     * @param min minimum allowed value
     * @param field field name used in exception messages
     * @throws ZibalValidationException if value is below minimum
     */
    public static void requireMin(long value, long min, String field) {
        if (value < min) {
            throw new ZibalValidationException(field + " must be at least " + min);
        }
    }

    /**
     * Requires an absolute HTTP or HTTPS URI.
     *
     * @param uri candidate URI
     * @param field field name used in exception messages
     * @throws ZibalValidationException if URI is null, relative, or unsupported scheme
     */
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

    /**
     * Requires an absolute HTTPS URI.
     *
     * @param uri candidate URI
     * @param field field name used in exception messages
     * @throws ZibalValidationException if URI is null, relative, or non-HTTPS
     */
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

    /**
     * Requires a 16-digit card number string.
     *
     * @param value card number
     * @param field field name used in exception messages
     * @throws ZibalValidationException if value is blank or malformed
     */
    public static void requireCardNumber(String value, String field) {
        requireNonBlank(value, field);
        String trimmed = value.trim();
        if (!CARD_PATTERN.matcher(trimmed).matches()) {
            throw new ZibalValidationException(field + " must be 16 digits");
        }
    }

    /**
     * Requires a 10-digit national code.
     *
     * @param value national code
     * @throws ZibalValidationException if value is blank or malformed
     */
    public static void requireNationalCode(String value) {
        requireNonBlank(value, "nationalCode");
        String trimmed = value.trim();
        if (!NATIONAL_CODE_PATTERN.matcher(trimmed).matches()) {
            throw new ZibalValidationException("nationalCode must be 10 digits");
        }
    }

    /**
     * Requires an Iranian IBAN in {@code IR########################} format.
     *
     * @param value IBAN value
     * @param field field name used in exception messages
     * @throws ZibalValidationException if value is blank or malformed
     */
    public static void requireIban(String value, String field) {
        requireNonBlank(value, field);
        String normalized = value.trim().toUpperCase(Locale.ROOT);
        if (!IBAN_PATTERN.matcher(normalized).matches()) {
            throw new ZibalValidationException(field + " must start with IR and be 26 characters");
        }
    }

    /**
     * Requires mode value to be either {@code 0} or {@code 1}; {@code null} is allowed.
     *
     * @param value mode value
     * @param field field name used in exception messages
     * @throws ZibalValidationException if value is not null and outside the allowed set
     */
    public static void requireMode(Integer value, String field) {
        if (value == null) {
            return;
        }
        if (value != 0 && value != 1) {
            throw new ZibalValidationException(field + " must be 0 or 1");
        }
    }

    /**
     * Normalizes a base URL by removing trailing slash characters.
     *
     * @param uri URI to normalize
     * @return normalized URI
     */
    public static URI normalizeBaseUrl(URI uri) {
        String value = uri.toString();
        while (value.endsWith("/")) {
            value = value.substring(0, value.length() - 1);
        }
        return URI.create(value);
    }
}
