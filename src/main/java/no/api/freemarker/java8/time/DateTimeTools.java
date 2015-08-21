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

import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateModelException;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.zone.ZoneRulesException;
import java.util.List;

/**
 * Helper methods and constants in use by the adapters.
 */
public final class DateTimeTools {

    public static final String METHOD_FORMAT = "format";
    public static final String METHOD_DAYS = "days";
    public static final String METHOD_MONTHS = "months";
    public static final String METHOD_YEARS = "years";
    public static final String METHOD_NANO = "nano";
    public static final String METHOD_SECONDS = "seconds";

    public static final String METHOD_UNKNOWN_MSG = "Unknown method call: ";
    public static final String ILLEGAL_ZONE_ID_MSG = "Illegal Zone ID";

    private DateTimeTools() {
        throw new UnsupportedOperationException();
    }

    /**
     * Create a DateTimeFormatter from a pattern found in a List on a given index.
     *
     * @param list
     *         A list of Strings containing the pattern
     * @param index
     *         The index on where in the list the pattern is located.
     * @param defaultFormatter
     *         A default formatter to be returned if the given list size is lower than the given index.
     *
     * @return A DateTimeFormatter for the given pattern, or the default formatter.
     */
    public static DateTimeFormatter createDateTimeFormatter(List list,
                                                            int index,
                                                            final DateTimeFormatter defaultFormatter) {
        if (list.size() > 0) {
            return DateTimeFormatter.ofPattern(((SimpleScalar) list.get(index)).getAsString());
        }
        return defaultFormatter;
    }

    /**
     * Create a DateTimeFormatter from a pattern found in a List on a given index.
     *
     * @param list
     *         A list of Strings containing the pattern
     * @param index
     *         The index on where in the list the pattern is located.
     * @param defaultPattern
     *         The pattern to be used for the formatter if the list size is lower than the given index.
     *
     * @return A DateTimeFormatter for the given pattern, or the default pattern.
     */
    public static DateTimeFormatter createDateTimeFormatter(List list,
                                                            int index,
                                                            final String defaultPattern) {
        return DateTimeFormatter.ofPattern(
                list.size() > index
                        ? ((SimpleScalar) list.get(index)).getAsString()
                        : defaultPattern);
    }

    /**
     * Look up a ZoneId based on a String in a list on a given index.
     *
     * @param list
     *         A list of Strings containing the String representation of the ZoneId.
     * @param index
     *         The index on where in the list the ZoneId string is located.
     *
     * @return A ZoneId instance for the given ZoneId string. If index is lower than the list size, then the default
     * FreeMarker ZoneId will be returned.
     *
     * @throws TemplateModelException
     *         If Illegal ZoneId string was found in the list.
     */
    public static ZoneId zoneIdLookup(List list, int index) throws TemplateModelException {
        ZoneId zoneId = Environment.getCurrentEnvironment().getTimeZone().toZoneId();
        if (list.size() > index) {
            String zoneIdString = ((SimpleScalar) list.get(index)).getAsString();
            try {
                zoneId = ZoneId.of(zoneIdString);
            } catch (ZoneRulesException e) {
                throw new TemplateModelException(ILLEGAL_ZONE_ID_MSG, e);
            }
        }
        return zoneId;
    }

}
