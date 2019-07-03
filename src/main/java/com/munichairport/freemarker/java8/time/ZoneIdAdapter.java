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

package com.munichairport.freemarker.java8.time;

import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.List;

import com.munichairport.freemarker.java8.config.Java8Configuration;

import freemarker.core.Environment;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;

/**
 * ZoneIdAdapter adds basic format support for {@link ZoneId} too FreeMarker 2.3.23 and above.
 */
public class ZoneIdAdapter extends AbstractAdapter<ZoneId> implements AdapterTemplateModel, TemplateScalarModel, TemplateHashModel {

    public ZoneIdAdapter(final ZoneId obj, final Java8Configuration configuration) {
        super(obj, configuration);
    }

    @Override
    public String getAsString() throws TemplateModelException {
        return getObject().getDisplayName(TextStyle.FULL, Environment.getCurrentEnvironment().getLocale());
    }

    @Override
    public TemplateModel get(final String s) throws TemplateModelException {
        if (DateTimeTools.METHOD_FORMAT.equals(s)) {
            return new ZoneIdFormatter(getObject());
        }
        throw new TemplateModelException(DateTimeTools.METHOD_UNKNOWN_MSG + s);
    }

    public class ZoneIdFormatter extends AbstractTextStyleLocaleFormatter<ZoneId> implements TemplateMethodModelEx {

        public ZoneIdFormatter(final ZoneId obj) {
            super(obj);
        }

        @Override
        public Object exec(final List list) throws TemplateModelException {
            return getObject().getDisplayName(findTextStyle(list), findLocale(list));
        }

    }
}
