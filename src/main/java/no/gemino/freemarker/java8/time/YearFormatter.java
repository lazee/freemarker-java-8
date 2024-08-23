package no.gemino.freemarker.java8.time;

import freemarker.template.TemplateMethodModelEx;
import no.gemino.freemarker.java8.zone.ZoneStrategy;

import java.time.Year;
import java.util.List;

import static no.gemino.freemarker.java8.time.DateTimeTools.createDateTimeFormatter;

public class YearFormatter extends AbstractFormatter<Year> implements TemplateMethodModelEx {

    public YearFormatter(Year obj, ZoneStrategy strategy) {
        super(obj, strategy);
    }

    @Override
    public Object exec(List list) {
        return getObject().format(createDateTimeFormatter(list, 0, DefaultFormatters.getYearFormatter()));
    }
}