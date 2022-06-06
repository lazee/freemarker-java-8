/*
 * Copyright (c) 2015-2022 Jakob Vad Nielsen
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

import freemarker.ext.beans.BeanModel;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.*;
import no.api.freemarker.java8.zone.ZoneStrategy;

import java.util.Objects;

/**
 * Abstract adapter used as a basis for all the other TemplateModel implementations in this package.
 *
 * @param <E> The java.time class that this TemplateModel is wrapping.
 */
public abstract class AbstractAdapter<E>
      extends WrappingTemplateModel
      implements AdapterTemplateModel, TemplateHashModel {

    private final E entity;
    private final BeanModel fallback;
    private final ZoneStrategy strategy;

    public AbstractAdapter(E entity, BeansWrapper wrapper, ZoneStrategy strategy) {
        this.entity = entity;
        this.strategy = strategy;
        this.fallback = new BeanModel(entity, Objects.requireNonNull(wrapper, "wrapper"));
    }

    protected abstract TemplateModel getForType(String key) throws TemplateModelException;

    public String getAsString() {
        return getObject().toString();
    }

    @Override
    public Object getAdaptedObject(Class aClass) {
        return entity;
    }

    @Override
    public final TemplateModel get(final String key) throws TemplateModelException {
        try {
            return getForType(key);
        } catch (final TemplateModelException ex) {
            try {
                return this.fallback.get(key);
            } catch (final TemplateModelException suppressed) {
                ex.addSuppressed(suppressed);
                throw ex;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public E getObject() {
        return entity;
    }

    public ZoneStrategy getStrategy() {
        return strategy;
    }
}
