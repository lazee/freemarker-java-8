package no.api.freemarker.java8.time;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.*;
import no.api.freemarker.java8.zone.ZoneStrategy;

import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

public class TemporalDialerAdapter extends AbstractAdapter<Temporal> implements AdapterTemplateModel,
      TemplateScalarModel, TemplateHashModel {

    private final TemplateHashModel delegate;

    public TemporalDialerAdapter(
          Temporal obj,
          BeansWrapper wrapper,
          TemplateHashModel delegate,
          ZoneStrategy strategy
    ) {
        super(obj, wrapper, strategy);
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
