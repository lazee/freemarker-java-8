package no.api.freemarker.java8.time;

import freemarker.template.AdapterTemplateModel;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;

import java.time.Period;

public class PeriodAdapter extends AbstractAdapter<Period> implements AdapterTemplateModel,
        TemplateScalarModel, TemplateHashModel {


    public PeriodAdapter(Period obj) {
        super(obj);
    }

    @Override
    public TemplateModel get(String s) throws TemplateModelException {
        if ("days".equalsIgnoreCase(s)) {
            return new SimpleNumber(getObject().getDays());
        } else if ("months".equalsIgnoreCase(s)) {
            return new SimpleNumber(getObject().getMonths());
        } else if ("years".equalsIgnoreCase(s)) {
            return new SimpleNumber(getObject().getYears());
        }
        throw new TemplateModelException("Unknown method call: " + s);
    }

}
