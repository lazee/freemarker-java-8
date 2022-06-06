package no.api.freemarker.java8.time;

import freemarker.template.TemplateMethodModelEx;
import no.api.freemarker.java8.zone.ZoneStrategy;

import java.time.YearMonth;
import java.util.List;

import static no.api.freemarker.java8.time.DateTimeTools.createDateTimeFormatter;

public class YearMonthFormatter extends AbstractFormatter<YearMonth> implements TemplateMethodModelEx {

    public YearMonthFormatter(YearMonth obj, ZoneStrategy strategy) {
        super(obj, strategy);
    }

    @Override
    public Object exec(List list) {
        return getObject().format(createDateTimeFormatter(list, 0, "yyyy-MM"));
    }
}