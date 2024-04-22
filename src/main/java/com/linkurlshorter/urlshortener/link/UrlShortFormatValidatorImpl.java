package com.linkurlshorter.urlshortener.link;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

/**
 * Implementation of a validator for checking the format of a short URL.
 * Used to check the format and uniqueness of short URLs.
 *
 * @author Vlas Pototskyi
 */
@RequiredArgsConstructor
public class UrlShortFormatValidatorImpl implements ConstraintValidator<UrlShortFormatValidator, String> {
    private static final Pattern SHORT_URL = Pattern.compile("^[a-zA-Z0-9]+$");
    private final LinkService linkService;

    /**
     * Checks whether the short URL matches the expected format and is unique.
     *
     * @param shortUrl short URL to validate
     * @param context  validation context
     * @return true if the short URL meets all validation criteria, false otherwise
     */
    @Override
    public boolean isValid(String shortUrl, ConstraintValidatorContext context) {
        if (shortUrl == null || shortUrl.isEmpty()) {
            context.buildConstraintViolationWithTemplate("Invalid short link!")
                    .addConstraintViolation();
            return false;
        }
        if (!shortUrl.matches(SHORT_URL.pattern())) {
            context.buildConstraintViolationWithTemplate("The following characters are not allowed!")
                    .addConstraintViolation();
            return false;
        }
        return !isExistShortLink(shortUrl, context);
    }

    private boolean isExistShortLink(String shortUrl, ConstraintValidatorContext context) {
        Link link = linkService.findByExistUniqueLink(shortUrl);
        if (link != null) {
            context.buildConstraintViolationWithTemplate("This link already exists!")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}