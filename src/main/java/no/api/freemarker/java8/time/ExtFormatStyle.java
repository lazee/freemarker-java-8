package no.api.freemarker.java8.time;

import java.time.format.FormatStyle;

import static java.time.format.FormatStyle.*;

public enum ExtFormatStyle {
    LONG_DATE(true, false, LONG),
    LONG_DATETIME(true, true, LONG),
    LONG_TIME(false, true, LONG),
    MEDIUM_DATE(true, false, MEDIUM),
    MEDIUM_DATETIME(true, true, MEDIUM),
    MEDIUM_TIME(false, true, MEDIUM),
    SHORT_DATE(true, false, SHORT),
    SHORT_DATETIME(true, true, SHORT),
    SHORT_TIME(false, true, SHORT);

    final boolean withDate;
    final boolean withTime;
    final FormatStyle javaFormatStyle;


    ExtFormatStyle(boolean withDate, boolean withTime, FormatStyle formatStyle) {
        this.withDate = withDate;
        this.withTime = withTime;
        this.javaFormatStyle = formatStyle;
    }
}
