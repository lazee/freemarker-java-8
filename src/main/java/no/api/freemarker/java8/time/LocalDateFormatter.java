package no.api.freemarker.java8.time;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import no.api.freemarker.java8.zone.ZoneStrategy;

import java.time.LocalDate;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static no.api.freemarker.java8.time.DateTimeTools.createDateTimeFormatter;

public class LocalDateFormatter extends AbstractFormatter<LocalDate> implements TemplateMethodModelEx {

    public LocalDateFormatter(LocalDate obj, ZoneStrategy strategy) {
        super(obj, strategy);
    }

    @Override
    public Object exec(List list) throws TemplateModelException {
        return getObject()
              .format(createDateTimeFormatter(list, 0, ISO_LOCAL_DATE).withZone(getTargetZoneId(list)));
    }
}