package no.api.freemarker.java8.time;

import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;

import java.time.Instant;
import java.util.List;

import static no.api.freemarker.java8.time.DateTimeTools.METHOD_FORMAT;
import static no.api.freemarker.java8.time.DateTimeTools.METHOD_UNKNOWN_MSG;

public class InstantAdapter extends AbstractAdapter<Instant> implements AdapterTemplateModel,
        TemplateScalarModel, TemplateHashModel {


    public InstantAdapter(Instant obj) {
        super(obj);
    }

    @Override
    public TemplateModel get(String s) throws TemplateModelException {
        if (METHOD_FORMAT.equals(s)) {
            return new InstantFormatter(getObject());
        }
        throw new TemplateModelException(METHOD_UNKNOWN_MSG + s);
    }

    public class InstantFormatter extends AbstractFormatter<Instant> implements TemplateMethodModelEx {

        public InstantFormatter(Instant obj) {
            super(obj);
        }

        @Override
        public Object exec(List list) throws TemplateModelException {
            return getObject().toString();
        }
    }
}
