package no.api.freemarker.java8.time;

import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;

import java.time.ZoneOffset;
import java.util.List;

import static no.api.freemarker.java8.time.DateTimeTools.METHOD_FORMAT;
import static no.api.freemarker.java8.time.DateTimeTools.METHOD_UNKNOWN_MSG;

public class ZoneOffsetAdapter extends AbstractAdapter<ZoneOffset> implements AdapterTemplateModel,
        TemplateScalarModel, TemplateHashModel {

    public ZoneOffsetAdapter(ZoneOffset obj) {
        super(obj);
    }

    @Override
    public TemplateModel get(String s) throws TemplateModelException {
        if (METHOD_FORMAT.equals(s)) {
            return new ZoneOffsetFormatter(getObject());
        }
        throw new TemplateModelException(METHOD_UNKNOWN_MSG + s);
    }

    public class ZoneOffsetFormatter extends AbstractTextStyleLocaleFormatter<ZoneOffset>
            implements TemplateMethodModelEx {

        public ZoneOffsetFormatter(ZoneOffset obj) {
            super(obj);
        }

        @Override
        public Object exec(List list) throws TemplateModelException {
            return getObject().getDisplayName(findTextStyle(list), findLocale(list));
        }
    }

}
