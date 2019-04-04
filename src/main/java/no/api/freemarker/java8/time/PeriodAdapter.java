/*
 * Copyright (c) 2015-2015 Amedia Utvikling AS.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package no.api.freemarker.java8.time;

import freemarker.template.AdapterTemplateModel;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;
import no.api.freemarker.java8.config.Java8Configuration;

import java.time.Period;

import static no.api.freemarker.java8.time.DateTimeTools.METHOD_DAYS;
import static no.api.freemarker.java8.time.DateTimeTools.METHOD_MONTHS;
import static no.api.freemarker.java8.time.DateTimeTools.METHOD_UNKNOWN_MSG;
import static no.api.freemarker.java8.time.DateTimeTools.METHOD_YEARS;

/**
 * PeriodAdapter gives access to the days, months and years values in the wrapped {@link Period} object in FreeMarker
 * templates.
 */
public class PeriodAdapter extends AbstractAdapter<Period> implements AdapterTemplateModel,
        TemplateScalarModel, TemplateHashModel {


    public PeriodAdapter(Period obj, Java8Configuration configuration) {
        super(obj, configuration);
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
