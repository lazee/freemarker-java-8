package no.gemino.freemarker.java8.time;

import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.*;

public class DefaultFormatters {

    private static DateTimeFormatter clockFormatter = ISO_LOCAL_DATE_TIME;

    private static DateTimeFormatter instantFormatter = ISO_LOCAL_DATE_TIME;

    private static DateTimeFormatter localDateFormatter = ISO_LOCAL_DATE;

    private static DateTimeFormatter localDateTimeFormatter = ISO_LOCAL_DATE_TIME;

    private static DateTimeFormatter localTimeFormatter = ISO_LOCAL_TIME;

    private static DateTimeFormatter monthDayFormatter = DateTimeFormatter.ofPattern("MM:dd");

    private static DateTimeFormatter offsetDateTimeFormatter = ISO_OFFSET_DATE_TIME;

    private static DateTimeFormatter offsetTimeFormatter = ISO_OFFSET_TIME;

    private static DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");

    private static DateTimeFormatter yearMonthFormatter = DateTimeFormatter.ofPattern("yyyy-MM");

    private static DateTimeFormatter zonedDateTimeFormatter = ISO_ZONED_DATE_TIME;

    public static DateTimeFormatter getClockFormatter() {
        return clockFormatter;
    }

    public static void setClockFormatter(DateTimeFormatter clockFormatter) {
        DefaultFormatters.clockFormatter = clockFormatter;
    }

    public static DateTimeFormatter getInstantFormatter() {
        return instantFormatter;
    }

    public static void setInstantFormatter(DateTimeFormatter instantFormatter) {
        DefaultFormatters.instantFormatter = instantFormatter;
    }

    public static DateTimeFormatter getLocalDateFormatter() {
        return localDateFormatter;
    }

    public static void setLocalDateFormatter(DateTimeFormatter localDateFormatter) {
        DefaultFormatters.localDateFormatter = localDateFormatter;
    }

    public static DateTimeFormatter getLocalDateTimeFormatter() {
        return localDateTimeFormatter;
    }

    public static void setLocalDateTimeFormatter(DateTimeFormatter localDateTimeFormatter) {
        DefaultFormatters.localDateTimeFormatter = localDateTimeFormatter;
    }

    public static DateTimeFormatter getLocalTimeFormatter() {
        return localTimeFormatter;
    }

    public static void setLocalTimeFormatter(DateTimeFormatter localTimeFormatter) {
        DefaultFormatters.localTimeFormatter = localTimeFormatter;
    }

    public static DateTimeFormatter getMonthDayFormatter() {
        return monthDayFormatter;
    }

    public static void setMonthDayFormatter(DateTimeFormatter monthDayFormatter) {
        DefaultFormatters.monthDayFormatter = monthDayFormatter;
    }

    public static DateTimeFormatter getOffsetDateTimeFormatter() {
        return offsetDateTimeFormatter;
    }

    public static void setOffsetDateTimeFormatter(DateTimeFormatter offsetDateTimeFormatter) {
        DefaultFormatters.offsetDateTimeFormatter = offsetDateTimeFormatter;
    }

    public static DateTimeFormatter getOffsetTimeFormatter() {
        return offsetTimeFormatter;
    }

    public static void setOffsetTimeFormatter(DateTimeFormatter offsetTimeFormatter) {
        DefaultFormatters.offsetTimeFormatter = offsetTimeFormatter;
    }

    public static DateTimeFormatter getYearFormatter() {
        return yearFormatter;
    }

    public static void setYearFormatter(DateTimeFormatter yearFormatter) {
        DefaultFormatters.yearFormatter = yearFormatter;
    }

    public static DateTimeFormatter getYearMonthFormatter() {
        return yearMonthFormatter;
    }

    public static void setYearMonthFormatter(DateTimeFormatter yearMonthFormatter) {
        DefaultFormatters.yearMonthFormatter = yearMonthFormatter;
    }

    public static DateTimeFormatter getZonedDateTimeFormatter() {
        return zonedDateTimeFormatter;
    }

    public static void setZonedDateTimeFormatter(DateTimeFormatter zonedDateTimeFormatter) {
        DefaultFormatters.zonedDateTimeFormatter = zonedDateTimeFormatter;
    }

}
