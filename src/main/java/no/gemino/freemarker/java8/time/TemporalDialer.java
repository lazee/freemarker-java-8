package no.gemino.freemarker.java8.time;

import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.List;

public class TemporalDialer extends AbstractMutator<Temporal> implements TemplateMethodModelEx {

    private final TemporalUnit temporalUnit;

    public TemporalDialer(Temporal obj, TemporalUnit temporalUnit) {
        super(obj);
        this.temporalUnit = temporalUnit;
    }

    @Override
    public Object exec(List list) throws TemplateModelException {
        return getObject().plus(parseLong(list.get(0)), temporalUnit);
    }

    private long parseLong(Object o) throws TemplateModelException {
        if (o instanceof Number) {
            return ((Number) o).longValue();
        } else if (o instanceof SimpleNumber) {
            return ((SimpleNumber) o).getAsNumber().longValue();
        } else if (o instanceof String) {
            try {
                return Long.parseLong((String) o);
            } catch (NumberFormatException nfe) {
                // suppress error
            }
        }
        throw new TemplateModelException("Invalid number: '" + o + "'");
    }
}