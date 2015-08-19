package no.api.freemarker.java8.time;

import freemarker.template.AdapterTemplateModel;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;

import java.time.Period;

import static no.api.freemarker.java8.time.DateTimeTools.METHOD_DAYS;
import static no.api.freemarker.java8.time.DateTimeTools.METHOD_MONTHS;
import static no.api.freemarker.java8.time.DateTimeTools.METHOD_UNKNOWN_MSG;
import static no.api.freemarker.java8.time.DateTimeTools.METHOD_YEARS;

public class PeriodAdapter extends AbstractAdapter<Period> implements AdapterTemplateModel,
        TemplateScalarModel, TemplateHashModel {


    public PeriodAdapter(Period obj) {
        super(obj);
    }

    @Override
    public TemplateModel get(String s) throws TemplateModelException {
        if (METHOD_DAYS.equalsIgnoreCase(s)) {
            return new SimpleNumber(getObject().getDays());
        } else if (METHOD_MONTHS.equalsIgnoreCase(s)) {
            return new SimpleNumber(getObject().getMonths());
        } else if (METHOD_YEARS.equalsIgnoreCase(s)) {
            return new SimpleNumber(getObject().getYears());
        }
        throw new TemplateModelException(METHOD_UNKNOWN_MSG + s);
    }

}
