package no.api.freemarker.java8.time;

import freemarker.template.AdapterTemplateModel;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;

import java.time.Duration;

import static no.api.freemarker.java8.time.DateTimeTools.METHOD_NANO;
import static no.api.freemarker.java8.time.DateTimeTools.METHOD_SECONDS;
import static no.api.freemarker.java8.time.DateTimeTools.METHOD_UNKNOWN_MSG;

public class DurationAdapter extends AbstractAdapter<Duration> implements AdapterTemplateModel,
        TemplateScalarModel, TemplateHashModel {

    public DurationAdapter(Duration obj) {
        super(obj);
    }

    @Override
    public TemplateModel get(String s) throws TemplateModelException {
        if (METHOD_NANO.equalsIgnoreCase(s)) {
            return new SimpleNumber(getObject().getNano());
        } else if (METHOD_SECONDS.equalsIgnoreCase(s)) {
            return new SimpleNumber(getObject().getSeconds());
        }
        throw new TemplateModelException(METHOD_UNKNOWN_MSG + s);
    }

}
