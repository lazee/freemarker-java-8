package no.api.freemarker.java8.time;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import no.api.freemarker.java8.zone.ZoneStrategy;

import java.time.Instant;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static no.api.freemarker.java8.time.DateTimeTools.createDateTimeFormatter;

public class InstantFormatter extends AbstractFormatter<Instant> implements TemplateMethodModelEx {

    public InstantFormatter(Instant obj, ZoneStrategy strategy) {
        super(obj, strategy);
    }

    @Override
    public Object exec(List list) throws TemplateModelException {
        return createDateTimeFormatter(list, 0, ISO_LOCAL_DATE)
              .withZone(getTargetZoneId(list))
              .format(getObject());
    }
}