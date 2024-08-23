package no.gemino.freemarker.java8.time;

import freemarker.template.TemplateMethodModelEx;

import java.time.ZoneId;
import java.util.List;

public class ZoneIdFormatter extends AbstractTextStyleLocaleFormatter<ZoneId> implements TemplateMethodModelEx {

    public ZoneIdFormatter(ZoneId obj) {
        super(obj);
    }

    @Override
    public Object exec(List list) {
        return getObject().getDisplayName(findTextStyle(list), findLocale(list));
    }
}