/*
 * Copyright (c) 2019 Flughafen München GmbH.
 *
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
 *
 * This file was modified by Flughafen München GmbH in order to add
 * or change the following functionality:
 *  - Added configuration support
 *  - Fallback to default bean model, if no internal method handler
 *    was found
 */

package com.munichairport.freemarker.java8.time;

import java.util.Objects;

import com.munichairport.freemarker.java8.config.Java8Configuration;

import freemarker.ext.beans.BeanModel;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * Abstract adapter used as a basis for all the other TemplateModel implementations in this package.
 *
 * @param <E> The java.time class that this TemplateModel is wrapping.
 */
public abstract class AbstractAdapter<E> implements AdapterTemplateModel, TemplateHashModel {

    private final E obj;

    private final BeanModel fallback;

    private final Java8Configuration configuration;

    public AbstractAdapter(final E obj, final Java8Configuration configuration, final BeansWrapper wrapper) {
        this.obj = obj;
        this.configuration = Objects.requireNonNull(configuration, "configuration");
        this.fallback = new BeanModel(obj, Objects.requireNonNull(wrapper, "wrapper"));
    }

    public String getAsString() throws TemplateModelException {
        return getObject().toString();
    }

    @Override
    public Object getAdaptedObject(final Class aClass) {
        return this.obj;
    }

    @Override
    public boolean isEmpty() throws TemplateModelException {
        return false;
    }

    @Override
    public final TemplateModel get(final String key) throws TemplateModelException {
        try {
            return getInternal(key);
        } catch (final TemplateModelException ex) {
            // Fallback to default bean model, if our implementation could not handle the key
            try {
                return this.fallback.get(key);
            } catch (final TemplateModelException suppressed) {
                ex.addSuppressed(suppressed);
                throw ex;
            }
        }
    }

    protected abstract TemplateModel getInternal(String key) throws TemplateModelException;

    public E getObject() {
        return this.obj;
    }

    public Java8Configuration getConfiguration() {
        return this.configuration;
    }
}
