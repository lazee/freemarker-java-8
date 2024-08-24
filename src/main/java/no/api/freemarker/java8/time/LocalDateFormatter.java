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

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import no.api.freemarker.java8.zone.ZoneStrategy;

import java.time.LocalDate;
import java.util.List;

import static no.api.freemarker.java8.time.DateTimeTools.createDateTimeFormatter;

public class LocalDateFormatter extends AbstractFormatter<LocalDate> implements TemplateMethodModelEx {

    public LocalDateFormatter(LocalDate obj, ZoneStrategy strategy) {
        super(obj, strategy);
    }

    @Override
    public Object exec(List list) throws TemplateModelException {
        return getObject()
              .format(createDateTimeFormatter(list, 0, DefaultFormatters.getLocalDateFormatter()));
    }
}