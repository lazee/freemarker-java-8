package no.gemino.freemarker.java8.time;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import no.gemino.freemarker.java8.zone.ZoneStrategy;

import java.time.LocalDateTime;
import java.util.List;

import static no.gemino.freemarker.java8.time.DateTimeTools.createDateTimeFormatter;

public class LocalDateTimeFormatter extends AbstractFormatter<LocalDateTime> implements TemplateMethodModelEx {

    public LocalDateTimeFormatter(LocalDateTime obj, ZoneStrategy strategy) {
        super(obj, strategy);
    }

    @Override
    public Object exec(List list) throws TemplateModelException {
        return getObject()
              .format(createDateTimeFormatter(list, 0, DefaultFormatters.getLocalDateTimeFormatter())
                    .withZone(getTargetZoneId(list, null)));
    }
}