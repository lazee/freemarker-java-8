package no.api.freemarker.java8.time;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public enum PreparedFormatStyle implements ExtFormatStyle {
    LONG_DATE(true, false, FormatStyle.LONG),
    LONG_DATETIME(true, true, FormatStyle.LONG),
    LONG_TIME(false, true, FormatStyle.LONG),
    MEDIUM_DATE(true, false, FormatStyle.MEDIUM),
    MEDIUM_DATETIME(true, true, FormatStyle.MEDIUM),
    MEDIUM_TIME(false, true, FormatStyle.MEDIUM),
    SHORT_DATE(true, false, FormatStyle.SHORT),
    SHORT_DATETIME(true, true, FormatStyle.SHORT),
    SHORT_TIME(false, true, FormatStyle.SHORT);

    private final DateTimeFormatter formatter;

    PreparedFormatStyle(final boolean withDate, final boolean withTime, final FormatStyle formatStyle) {
        if (withDate && withTime) {
            this.formatter = DateTimeFormatter.ofLocalizedDateTime(formatStyle);
        } else if (withDate) {
            this.formatter = DateTimeFormatter.ofLocalizedDate(formatStyle);
        } else {
            this.formatter = DateTimeFormatter.ofLocalizedTime(formatStyle);
        }
    }

    @Override
    public DateTimeFormatter getFormatter() {
        return this.formatter;
    }
}
