package no.api.freemarker.java8.time;

import freemarker.template.TemplateMethodModelEx;

import java.time.ZoneOffset;
import java.util.List;

public class ZoneOffsetFormatter extends AbstractTextStyleLocaleFormatter<ZoneOffset>
      implements TemplateMethodModelEx {

    public ZoneOffsetFormatter(ZoneOffset obj) {
        super(obj);
    }

    @Override
    public Object exec(List list) {
        return getObject().getDisplayName(findTextStyle(list), findLocale(list));
    }
}