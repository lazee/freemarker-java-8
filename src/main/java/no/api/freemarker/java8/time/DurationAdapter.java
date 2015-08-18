package no.api.freemarker.java8.time;

import freemarker.template.AdapterTemplateModel;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;

import java.time.Duration;

public class DurationAdapter extends AbstractAdapter<Duration> implements AdapterTemplateModel,
        TemplateScalarModel, TemplateHashModel {

    public DurationAdapter(Duration obj) {
        super(obj);
    }

    @Override
    public TemplateModel get(String s) throws TemplateModelException {
        if ("nano".equalsIgnoreCase(s)) {
            return new SimpleNumber(getObject().getNano());
        } else if ("seconds".equalsIgnoreCase(s)) {
            return new SimpleNumber(getObject().getSeconds());
        }
        throw new TemplateModelException("Unknown method call: " + s);
    }

}
