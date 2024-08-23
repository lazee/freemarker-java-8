package no.gemino.freemarker.java8.time;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.time.LocalDate;
import java.util.List;

import static no.gemino.freemarker.java8.time.DateTimeTools.*;

public class LocalDateChecker extends AbstractChecker<LocalDate> implements TemplateMethodModelEx {
    private final String method;

    public LocalDateChecker(LocalDate obj, String method) {
        super(obj);
        this.method = method;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Object exec(List list) throws TemplateModelException {
        AbstractAdapter adapter = (AbstractAdapter) list.get(0);
        Object object = adapter.getObject();
        if (object instanceof LocalDate) {
            LocalDate other = (LocalDate) object;
            switch (method) {
                case METHOD_EQUALS:
                    return getObject().equals(other);
                case METHOD_AFTER:
                    return getObject().isAfter(other);
                case METHOD_BEFORE:
                    return getObject().isBefore(other);
            }
            throw new TemplateModelException("method not implemented");
        } else {
            throw new TemplateModelException("Invalid operand type for " + method + ": " + object);
        }
    }
}