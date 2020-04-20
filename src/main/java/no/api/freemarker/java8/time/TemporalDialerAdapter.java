package no.api.freemarker.java8.time;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;

import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

public class TemporalDialerAdapter extends AbstractAdapter<Temporal> implements AdapterTemplateModel, TemplateScalarModel, TemplateHashModel {

    private final TemplateHashModel delegate;
    public TemporalDialerAdapter(Temporal obj, BeansWrapper wrapper, TemplateHashModel delegate) {
        super(obj, wrapper);
        this.delegate = delegate;
    }

    @Override
    protected TemplateModel getForType(String methodName) throws TemplateModelException {
        TemporalUnit temporalUnit = new MethodNameToTemporalUnitMapper().map(methodName);
        if (temporalUnit == null) {
            return delegate.get(methodName);
        } else {
            return new TemporalDialer(this.getObject(), temporalUnit);
        }
    }

}
