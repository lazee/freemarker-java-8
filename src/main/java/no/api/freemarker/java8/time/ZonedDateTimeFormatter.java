package no.api.freemarker.java8.time;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import no.api.freemarker.java8.zone.ZoneStrategy;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;

public class ZonedDateTimeFormatter extends AbstractFormatter<ZonedDateTime> implements TemplateMethodModelEx {

    public ZonedDateTimeFormatter(ZonedDateTime obj, ZoneStrategy strategy) {
        super(obj, strategy);
    }

    @Override
    public Object exec(final List list) throws TemplateModelException {
        final ZoneId targetZoneId = getTargetZoneId(list);
        if (isDifferentTimeZoneRequested(targetZoneId)) {
            return getObject().withZoneSameInstant(targetZoneId).format(DateTimeTools
                  .createDateTimeFormatter(list, 0, ISO_ZONED_DATE_TIME));
        } else {
            return getObject()
                  .format(DateTimeTools.createDateTimeFormatter(list, 0, ISO_ZONED_DATE_TIME));
        }
    }

    private boolean isDifferentTimeZoneRequested(ZoneId zoneId) {
        return !getObject().getZone().equals(zoneId);
    }
}