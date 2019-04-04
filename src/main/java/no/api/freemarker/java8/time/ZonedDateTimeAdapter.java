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

import static no.api.freemarker.java8.time.DateTimeTools.METHOD_FORMAT;
import static no.api.freemarker.java8.time.DateTimeTools.METHOD_UNKNOWN_MSG;
import static no.api.freemarker.java8.time.DateTimeTools.createDateTimeFormatter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;
import no.api.freemarker.java8.config.Java8Configuration;

/**
 * ZonedDateTimeAdapter adds basic format support for {@link ZonedDateTime} too FreeMarker 2.3.23 and above.
 */
public class ZonedDateTimeAdapter extends AbstractAdapter<ZonedDateTime> implements AdapterTemplateModel,
        TemplateScalarModel, TemplateHashModel {

    public ZonedDateTimeAdapter(ZonedDateTime obj, Java8Configuration configuration) {
        super(obj, configuration);
    }

    public TemplateModel get(String s) throws TemplateModelException {
        if (METHOD_FORMAT.equals(s)) {
            return new ZonedDateTimeFormatter(getObject());
        }
        throw new TemplateModelException(METHOD_UNKNOWN_MSG + s);
    }

    public class ZonedDateTimeFormatter extends AbstractFormatter<ZonedDateTime> implements TemplateMethodModelEx {

        public ZonedDateTimeFormatter(ZonedDateTime obj) {
            super(obj);
        }
        
        public Object exec(List list) throws TemplateModelException {
        	final ZoneId targetZoneId = getTargetZoneId(list);
            if (isDifferentTimeZoneRequested(targetZoneId)) {
                return getObject().withZoneSameInstant(targetZoneId).format(
                        createDateTimeFormatter(list, 0, DateTimeFormatter.ISO_ZONED_DATE_TIME));
            } else {
                return getObject().format(createDateTimeFormatter(list, 0, DateTimeFormatter.ISO_ZONED_DATE_TIME));
            }
        }
        
        private ZoneId getTargetZoneId(List argumentList) throws TemplateModelException {
        	Optional<ZoneId> zoneId = DateTimeTools.zoneIdLookup(argumentList, 1);
        	if(zoneId.isPresent())
        		return zoneId.get();
            return getConfiguration().getTimezoneStrategy().getUpdatedZone(getObject().getZone());
        }

        private boolean isDifferentTimeZoneRequested(ZoneId zoneId) {
            return !getObject().getZone().equals(zoneId);
        }


    }

}
