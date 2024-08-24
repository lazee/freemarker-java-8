/*
 * Copyright (c) 2015-2024 Jakob Vad Nielsen
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