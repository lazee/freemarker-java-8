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
