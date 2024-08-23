package no.gemino.freemarker.java8.time;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import no.gemino.freemarker.java8.zone.ZoneStrategy;

import java.time.MonthDay;
import java.util.List;

import static no.gemino.freemarker.java8.time.DateTimeTools.createDateTimeFormatter;

public class MonthDayFormatter extends AbstractFormatter<MonthDay> implements TemplateMethodModelEx {

    public MonthDayFormatter(MonthDay obj, ZoneStrategy strategy) {
        super(obj, strategy);
    }

    @Override
    public Object exec(List list) throws TemplateModelException {
        return getObject().format(createDateTimeFormatter(list, 0, DefaultFormatters.getMonthDayFormatter()));
    }
}