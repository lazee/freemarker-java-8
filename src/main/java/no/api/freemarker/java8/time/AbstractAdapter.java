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
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;
import freemarker.template.WrappingTemplateModel;

/**
 * Abstract adapter used as a basis for all the other TemplateModel implementations in this package.
 *
 * @param <E>
 *         The java.time class that this TemplateModel is wrapping.
 */
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
