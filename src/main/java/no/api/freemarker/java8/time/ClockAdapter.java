package no.api.freemarker.java8.time;

import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;

import java.time.Clock;
import java.util.List;

import static no.api.freemarker.java8.time.DateTimeTools.METHOD_FORMAT;
import static no.api.freemarker.java8.time.DateTimeTools.METHOD_UNKNOWN_MSG;

public class ClockAdapter extends AbstractAdapter<Clock> implements AdapterTemplateModel,
        TemplateScalarModel, TemplateHashModel {

    public ClockAdapter(Clock obj) {
        super(obj);
    }

    @Override
    public TemplateModel get(String s) throws TemplateModelException {
        if (METHOD_FORMAT.equals(s)) {
            return new ClockFormatter(getObject());
        }
        throw new TemplateModelException(METHOD_UNKNOWN_MSG + s);
    }

    /**
     * This is a quit silly implementation. Normally you would like to convert to an Instant when printing the clock.
     *
     * @return String representation of the clock.
     * @throws TemplateModelException If no string representation could be created.
     */
    @Override
    public String getAsString() throws TemplateModelException {
        return getObject().toString();
    }

    public class ClockFormatter extends AbstractFormatter<Clock> implements TemplateMethodModelEx {

        public ClockFormatter(Clock obj) {
            super(obj);
        }

        @Override
        public Object exec(List list) throws TemplateModelException {
            return getObject().toString();
        }
    }
}
