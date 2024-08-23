package no.gemino.freemarker.java8.time;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import no.gemino.freemarker.java8.zone.ZoneStrategy;

import java.time.Clock;
import java.util.List;

import static no.gemino.freemarker.java8.time.DateTimeTools.createDateTimeFormatter;

public class ClockFormatter extends AbstractFormatter<Clock> implements TemplateMethodModelEx {

    public ClockFormatter(Clock obj, ZoneStrategy strategy) {
        super(obj, strategy);
    }

    @Override
    public Object exec(List list) throws TemplateModelException {
        return createDateTimeFormatter(list, 0, DefaultFormatters.getClockFormatter())
              .withZone(getTargetZoneId(list, null))
              .format(getObject().instant());
    }

}