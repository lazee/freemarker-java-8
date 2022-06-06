package no.api.freemarker.java8.zone;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Defines the general strategy used when dealing with zones in different parts of the DateTime API.
 */
public interface ZoneStrategy {

    /**
     * Return the ZoneId to use for formatting a java.time object.
     *
     * @param input The current {@link ZoneId} provided by a java.time object or method parameter.
     * @return The {@link ZoneId} that should be used for formatting the {@link ZonedDateTime} object.
     */
    ZoneId getZoneId(ZoneId input);

    /**
     * Return the ZoneId to use for formatting a java.time object, when no zone information exists on object and are
     * not given as a parameter.
     *
     * @return The {@link ZoneId} that should be used for formatting the object.
     */
    ZoneId getZoneId();
}
