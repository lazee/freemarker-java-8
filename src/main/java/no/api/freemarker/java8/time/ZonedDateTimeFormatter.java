package no.api.freemarker.java8.time;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import no.api.freemarker.java8.zone.ZoneStrategy;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static no.api.freemarker.java8.time.DateTimeTools.createDateTimeFormatter;

public class ZonedDateTimeFormatter extends AbstractFormatter<ZonedDateTime> implements TemplateMethodModelEx {

    public ZonedDateTimeFormatter(ZonedDateTime obj, ZoneStrategy strategy) {
        super(obj, strategy);
    }

    @Override
    public Object exec(final List list) throws TemplateModelException {

        final ZoneId targetZoneId = getTargetZoneId(list, getObject().getZone());

        if (isDifferentTimeZoneRequested(targetZoneId)) {
            return getObject().withZoneSameInstant(targetZoneId)
                              .format(createDateTimeFormatter(list, 0, DefaultFormatters.getZonedDateTimeFormatter()));
        } else {
            return getObject()
                  .format(createDateTimeFormatter(list, 0, DefaultFormatters.getZonedDateTimeFormatter()));
        }
    }

    private boolean isDifferentTimeZoneRequested(ZoneId zoneId) {
        return !getObject().getZone().equals(zoneId);
    }
}