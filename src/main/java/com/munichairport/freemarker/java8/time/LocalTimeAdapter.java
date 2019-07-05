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
 *  - Only support isEqual, because isBefore and isAfter are
 *    already supported by the default bean model
 */

package com.munichairport.freemarker.java8.time;

import static com.munichairport.freemarker.java8.time.DateTimeTools.METHOD_EQUALS;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.munichairport.freemarker.java8.config.Java8Configuration;

import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;

/**
 * LocalTimeAdapter adds basic format support for {@link LocalTime} too FreeMarker 2.3.23 and above.
 */
public class LocalTimeAdapter extends AbstractAdapter<LocalTime> implements AdapterTemplateModel, TemplateScalarModel, TemplateHashModel {

    public LocalTimeAdapter(final LocalTime obj, final Java8Configuration configuration) {
        super(obj, configuration);
    }

    @Override
    protected TemplateModel getInternal(final String s) throws TemplateModelException {
        if (DateTimeTools.METHOD_FORMAT.equals(s)) {
            return new LocalTimeFormatter(getObject());
        } else if (DateTimeTools.METHOD_EQUALS.equals(s)) {
            // LocalTime has no isEqual(...) method, because equals(...) has the same functionality
            return new LocalTimeChecker(getObject(), s);
        }
        throw new TemplateModelException(DateTimeTools.METHOD_UNKNOWN_MSG + s);
    }

    public class LocalTimeFormatter extends AbstractFormatter<LocalTime> implements TemplateMethodModelEx {

        public LocalTimeFormatter(final LocalTime obj) {
            super(obj);
        }

        @Override
        public Object exec(final List list) throws TemplateModelException {
            return getObject().format(DateTimeTools.createDateTimeFormatter(list, 0, DateTimeFormatter.ISO_LOCAL_TIME));
        }
    }

    public class LocalTimeChecker extends AbstractChecker<LocalTime> implements TemplateMethodModelEx {

        private final String method;

        public LocalTimeChecker(final LocalTime obj, final String method) {
            super(obj);
            this.method = method;
        }

        @Override
        public Object exec(final List list) throws TemplateModelException {
            final LocalTimeAdapter adapter = (LocalTimeAdapter) list.get(0);
            switch (this.method) {
                case METHOD_EQUALS:
                    return getObject().equals(adapter.getObject());
            }
            throw new TemplateModelException("method '" + this.method + "' not implemented");
        }
    }
}
