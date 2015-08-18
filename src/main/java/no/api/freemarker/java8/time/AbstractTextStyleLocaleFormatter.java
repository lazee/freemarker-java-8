package no.api.freemarker.java8.time;

import freemarker.core.Environment;

import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class AbstractTextStyleLocaleFormatter<E> extends AbstractFormatter<E> {

    public AbstractTextStyleLocaleFormatter(E obj) {
        super(obj);
    }

    public TextStyle findTextStyle(List list) {
        if (list.size() > 0) {
            return TextStyle.valueOf(list.get(0).toString().toUpperCase());
        }
        return TextStyle.FULL;
    }

    public Locale findLocale(List list) {
        if (list.size() > 1) {
            return Locale.forLanguageTag(list.get(1).toString());
        }
        return Environment.getCurrentEnvironment().getLocale();
    }
}
