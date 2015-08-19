package no.api.freemarker.java8.time;

import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateModelException;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.zone.ZoneRulesException;
import java.util.List;

public final class DateTimeTools {

    public static final String METHOD_FORMAT = "format";
    public static final String METHOD_DAYS = "days";
    public static final String METHOD_MONTHS = "months";
    public static final String METHOD_YEARS = "years";
    public static final String METHOD_NANO = "nano";
    public static final String METHOD_SECONDS = "seconds";


    public static final String METHOD_UNKNOWN_MSG = "Unknown method call: ";
    public static final String ILLEGAL_ZONE_ID_MSG = "Illegal Zone ID";

    private DateTimeTools() {
        throw new UnsupportedOperationException();
    }

    public static DateTimeFormatter createDateTimeFormatter(List list, int index, DateTimeFormatter defaultFormatter) {
        if (list.size() > 0) {
            return DateTimeFormatter.ofPattern(((SimpleScalar) list.get(index)).getAsString());
        }
        return defaultFormatter;
    }

    public static DateTimeFormatter createDateTimeFormatter(List list, int index, final String defaultPattern) {
        return DateTimeFormatter.ofPattern(
                list.size() > (index - 1)
                        ? ((SimpleScalar) list.get(index)).getAsString()
                        : defaultPattern);
    }

    public static ZoneId zoneIdLookup(List list, int index) throws TemplateModelException {
        ZoneId zoneId = Environment.getCurrentEnvironment().getTimeZone().toZoneId();
        if (list.size() > (index - 1)) {
            String zoneIdString = ((SimpleScalar) list.get(index)).getAsString();
            try {
                zoneId = ZoneId.of(zoneIdString);
            } catch (ZoneRulesException e) {
                throw new TemplateModelException(ILLEGAL_ZONE_ID_MSG, e);
            }
        }
        return zoneId;
    }

}
