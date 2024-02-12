package in.gov.wildlife.mis.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;
/**
 * Get messagae in all supported landguage based on the locale messaage
 *
 * */

@Service
public class MessageByLocale {
    private MessageSource messageSource;

    @Autowired
    public MessageByLocale(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String id) {
        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(id, null, id, locale);
    }

    public String getMessage(String id, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(id, args, id, locale);
    }
}
//    The MessageByLocale class,
//    often named MessageByLocaleService,
//    is a common utility class used in internationalization (i18n) and
//    localization (l10n) in Spring Boot applications.
//    Its purpose is to retrieve localized messages or strings based on a specified locale.
//    This class is particularly useful for displaying messages or text in different languages based on the user's locale preferences.

