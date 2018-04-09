package no.api.freemarker.java8.time;

import java.time.format.FormatStyle;

public enum ExtFormatStyle {
    LONG_DATE(true, false, FormatStyle.LONG),
    LONG_DATETIME(true, true, FormatStyle.LONG),
    LONG_TIME(false, true, FormatStyle.LONG),
    MEDIUM_DATE(true, false, FormatStyle.MEDIUM),
    MEDIUM_DATETIME(true, true, FormatStyle.MEDIUM),
    MEDIUM_TIME(false, true, FormatStyle.MEDIUM),
    SHORT_DATE(true, false, FormatStyle.SHORT),
    SHORT_DATETIME(true, true, FormatStyle.SHORT),
    SHORT_TIME(false, true, FormatStyle.SHORT);

    final boolean withDate;
    final boolean withTime;
    final FormatStyle javaFormatStyle;

    ExtFormatStyle(boolean withDate, boolean withTime, FormatStyle formatStyle) {
        this.withDate = withDate;
        this.withTime = withTime;
        this.javaFormatStyle = formatStyle;
    }
}
