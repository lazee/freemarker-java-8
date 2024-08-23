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

package no.gemino.freemarker.java8.time;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.*;
import no.gemino.freemarker.java8.zone.ZoneStrategy;

import java.time.ZonedDateTime;

import static no.gemino.freemarker.java8.time.DateTimeTools.METHOD_FORMAT;
import static no.gemino.freemarker.java8.time.DateTimeTools.METHOD_UNKNOWN_MSG;

/**
 * ZonedDateTimeAdapter adds basic format support for {@link ZonedDateTime} too FreeMarker 2.3.23 and above.
 */
public class ZonedDateTimeAdapter extends AbstractAdapter<ZonedDateTime> implements AdapterTemplateModel,
      TemplateScalarModel, TemplateHashModel {

    public ZonedDateTimeAdapter(ZonedDateTime obj, BeansWrapper wrapper, ZoneStrategy strategy) {
        super(obj, wrapper, strategy);
    }

    public TemplateModel getForType(String s) throws TemplateModelException {
        if (METHOD_FORMAT.equals(s)) {
            return new ZonedDateTimeFormatter(getObject(), getStrategy());
        }
        throw new TemplateModelException(METHOD_UNKNOWN_MSG + s);
    }
}
