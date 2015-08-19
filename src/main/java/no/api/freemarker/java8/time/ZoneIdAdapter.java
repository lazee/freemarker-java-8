package no.api.freemarker.java8.time;

import freemarker.core.Environment;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;

import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.List;

import static no.api.freemarker.java8.time.DateTimeTools.METHOD_FORMAT;
import static no.api.freemarker.java8.time.DateTimeTools.METHOD_UNKNOWN_MSG;

public class ZoneIdAdapter extends AbstractAdapter<ZoneId>
        implements AdapterTemplateModel, TemplateScalarModel, TemplateHashModel {

    public ZoneIdAdapter(ZoneId obj) {
        super(obj);
    }

    @Override
    public String getAsString() throws TemplateModelException {
        return getObject().getDisplayName(TextStyle.FULL, Environment.getCurrentEnvironment().getLocale());
    }

    @Override
    public TemplateModel get(String s) throws TemplateModelException {
        if (METHOD_FORMAT.equals(s)) {
            return new ZoneIdFormatter(getObject());
        }
        throw new TemplateModelException(METHOD_UNKNOWN_MSG + s);
    }

    public class ZoneIdFormatter extends AbstractTextStyleLocaleFormatter<ZoneId> implements TemplateMethodModelEx {

        public ZoneIdFormatter(ZoneId obj) {
            super(obj);
        }

        @Override
        public Object exec(List list) throws TemplateModelException {
            return getObject().getDisplayName(findTextStyle(list), findLocale(list));
        }

    }
}
