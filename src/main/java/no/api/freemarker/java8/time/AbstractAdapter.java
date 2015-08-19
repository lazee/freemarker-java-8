package no.api.freemarker.java8.time;

import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;
import freemarker.template.WrappingTemplateModel;

public abstract class AbstractAdapter<E>
        extends WrappingTemplateModel
        implements AdapterTemplateModel, TemplateHashModel {

    private E obj;

    public AbstractAdapter(E obj) {
        this.obj = obj;
    }

    public String getAsString() throws TemplateModelException {
        return getObject().toString();
    }

    @Override
    public Object getAdaptedObject(Class aClass) {
        return obj;
    }

    @Override
    public boolean isEmpty() throws TemplateModelException {
        return false;
    }

    public E getObject() {
        return obj;
    }
}
