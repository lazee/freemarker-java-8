package no.api.freemarker.java8.time;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import no.api.freemarker.java8.zone.ZoneStrategy;

import java.time.OffsetDateTime;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;
import static no.api.freemarker.java8.time.DateTimeTools.createDateTimeFormatter;

public class OffsetDateTimeFormatter extends AbstractFormatter<OffsetDateTime> implements TemplateMethodModelEx {

    public OffsetDateTimeFormatter(OffsetDateTime obj, ZoneStrategy strategy) {
        super(obj, strategy);
    }

    @Override
    public Object exec(List list) throws TemplateModelException {
        return getObject().format(
              createDateTimeFormatter(list, 0, ISO_OFFSET_DATE_TIME)
                    .withZone(getTargetZoneId(list, getObject().getOffset().normalized()))
        );
    }
}