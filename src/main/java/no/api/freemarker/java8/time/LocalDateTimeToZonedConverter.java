package no.api.freemarker.java8.time;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import no.api.freemarker.java8.zone.ZoneStrategy;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public class LocalDateTimeToZonedConverter extends AbstractFormatter<LocalDateTime> implements TemplateMethodModelEx {

    public LocalDateTimeToZonedConverter(LocalDateTime obj, ZoneStrategy strategy) {
        super(obj, strategy);
    }

    @Override
    public Object exec(List list) throws TemplateModelException {
        return ZonedDateTime.of(getObject(), getTargetZoneId(list, null));
    }
}