package no.api.freemarker.java8;

import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.Version;
import no.api.freemarker.java8.time.TemporalDialerAdapter;
import no.api.freemarker.java8.zone.ZonedDateTimeStrategy;

import java.time.temporal.Temporal;

/**
 * Freemarker ObjectWrapper that extends the Java8ObjectWrapper with support for time manipulations in all temporal classes in the new java.time api.
 * Such time manipulation operations include plusSeconds, plusMinutes, etc.
 */
public class EnhancedJava8ObjectWrapper extends Java8ObjectWrapper {
    public EnhancedJava8ObjectWrapper(Version incompatibleImprovements) {
        super(incompatibleImprovements);
    }

    public EnhancedJava8ObjectWrapper(Version incompatibleImprovements, ZonedDateTimeStrategy strategy) {
        super(incompatibleImprovements, strategy);
    }

    @Override
    protected TemplateModel handleUnknownType(Object obj) throws TemplateModelException {
        TemplateModel delegate = super.handleUnknownType(obj);
        return obj instanceof Temporal && delegate instanceof TemplateHashModel
                ? new TemporalDialerAdapter((Temporal) obj, this, (TemplateHashModel) delegate)
                : delegate;
    }
}
