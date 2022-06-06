package no.api.freemarker.java8.time;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.time.LocalDateTime;
import java.util.List;

import static no.api.freemarker.java8.time.DateTimeTools.*;

public class LocalDateTimeChecker extends AbstractChecker<LocalDateTime> implements TemplateMethodModelEx {
    private final String method;

    public LocalDateTimeChecker(LocalDateTime obj, String method) {
        super(obj);
        this.method = method;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Object exec(List list) throws TemplateModelException {
        AbstractAdapter adapter = (AbstractAdapter) list.get(0);
        Object object = adapter.getObject();
        if (object instanceof LocalDateTime) {
            LocalDateTime other = (LocalDateTime) object;
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