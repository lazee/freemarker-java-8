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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.zone.ZoneRulesException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateModelException;

/**
 * Helper methods and constants in use by the adapters.
 */
public final class DateTimeTools {

    public static final String METHOD_EQUALS = "isEqual";
    public static final String METHOD_BEFORE = "isBefore";
    public static final String METHOD_AFTER = "isAfter";
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
            String format = ((SimpleScalar) list.get(index)).getAsString();
            ExtFormatStyle style = getFormatStyle(format);

            if(style != null) {
            	return style.getFormatter().withLocale(getLocale());
            }
            Optional<DateTimeFormatter> builtin = getJreBuiltinFormatter(format);
            return builtin.orElseGet(() -> DateTimeFormatter.ofPattern(format, getLocale()));
        }
        return defaultFormatter.withLocale(getLocale());
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
                        : defaultPattern, getLocale());
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
    public static Optional<ZoneId> zoneIdLookup(List list, int index) throws TemplateModelException {
        if (list.size() > index) {
            String zoneIdString = ((SimpleScalar) list.get(index)).getAsString();
            try {
                return Optional.of(ZoneId.of(zoneIdString));
            } catch (ZoneRulesException e) {
                throw new TemplateModelException(ILLEGAL_ZONE_ID_MSG, e);
            }
        }
        return Optional.empty();
    }

    private static Locale getLocale() {
        if (Environment.getCurrentEnvironment() != null) {
            return Environment.getCurrentEnvironment().getLocale();
        } else {
            return Locale.getDefault();
        }
    }

    private static ExtFormatStyle getFormatStyle(String format) {
        try {
            return PreparedFormatStyle.valueOf(format);
        } catch(IllegalArgumentException | NullPointerException ex) {
            return null;
        }
    }
    
    private static Optional<DateTimeFormatter> getJreBuiltinFormatter(String name) {
    	try {
    		final Field dateTimeFormatterField = DateTimeFormatter.class.getField(name);
    		if((dateTimeFormatterField.getModifiers() & Modifier.STATIC) != 0 // Check if field is static
    				&& DateTimeFormatter.class.isAssignableFrom(dateTimeFormatterField.getType())) {
    			return Optional.ofNullable((DateTimeFormatter) dateTimeFormatterField.get(null));
    		}
    		// Not static, or not of the correct type
    		return Optional.empty();
    	} catch (NoSuchFieldException e) {
    		// Seems like name is no built in DateTimeFormatter
    		return Optional.empty();
    	} catch (IllegalArgumentException e) {
			// As this field is checked to be static, this should never occur
			throw new RuntimeException("Field \"" + name + "\" has modifier STATIC but seems to be not static!", e);
		} catch (IllegalAccessException e) {
			// Well, if you use a SecurityManager, we cannot do this
			throw new RuntimeException("Not allowed to access Field \"" + name + "\" of class " + DateTimeFormatter.class, e);
		}
    }

}
