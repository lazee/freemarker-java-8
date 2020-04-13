package no.api.freemarker.java8.time;

import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;

import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

public class TemporalDialerAdapter extends AbstractAdapter<Temporal> implements AdapterTemplateModel, TemplateScalarModel, TemplateHashModel {

    private final TemplateHashModel delegate;
    public TemporalDialerAdapter(Temporal obj, TemplateHashModel delegate) {
        super(obj);
        this.delegate = delegate;
    }

    @Override
    public TemplateModel get(String methodName) throws TemplateModelException {
        TemporalUnit temporalUnit = new MethodNameToTemporalUnitMapper().map(methodName);
        if (temporalUnit == null) {
            return delegate.get(methodName);
        } else {
            return new TemporalDialer(this.getObject(), temporalUnit);
        }
    }

}
