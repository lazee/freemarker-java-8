package no.api.freemarker.java8.time;

import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;

import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static no.api.freemarker.java8.time.DateTimeTools.METHOD_FORMAT;
import static no.api.freemarker.java8.time.DateTimeTools.METHOD_UNKNOWN_MSG;
import static no.api.freemarker.java8.time.DateTimeTools.createDateTimeFormatter;

public class OffsetTimeAdapter extends AbstractAdapter<OffsetTime> implements AdapterTemplateModel,
        TemplateScalarModel, TemplateHashModel {

    public OffsetTimeAdapter(OffsetTime obj) {
        super(obj);
    }

    @Override
    public TemplateModel get(String s) throws TemplateModelException {
        if (METHOD_FORMAT.equals(s)) {
            return new OffsetTimeFormatter(getObject());
        }
        throw new TemplateModelException(METHOD_UNKNOWN_MSG + s);
    }

    public class OffsetTimeFormatter extends AbstractFormatter<OffsetTime> implements TemplateMethodModelEx {

        public OffsetTimeFormatter(OffsetTime obj) {
            super(obj);
        }

        @Override
        public Object exec(List list) throws TemplateModelException {
            return getObject().format(createDateTimeFormatter(list, 0, DateTimeFormatter.ISO_OFFSET_TIME));
        }
    }
}
