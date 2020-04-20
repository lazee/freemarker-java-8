package no.api.freemarker.java8.zone;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Can transform a given {@link ZoneId} into another, based on rules set by the implementing classes.
 *
 * <p>Within freemarker-java-8 this is used only within the {@link no.api.freemarker.java8.time.ZonedDateTimeAdapter} to
 * figure out what {@link ZoneId} to use when formatting a {@link ZonedDateTime} into a {@link String}.</p>
 *
 * <h3>Example</h3>
 *
 * <pre>
 *     zoned_date_time_object.format('yyyy-MM-dd Z')
 * </pre>
 *
 * <p>
 * In the about example the {@link ZonedDateTime} object will by default be formatted with the same timezone as found
 * inside the object. This was not the case in earlier versions of the library, where we would use the system timezone.
 * But this might not be the wanted default behaviour for everybody. By introducing this interface it is possible
 * to control what timezone to use as default when formatting a {@link ZonedDateTime} and no timezone is specified in
 * the format method.
 * </p>
 */
public interface ZonedDateTimeStrategy {

    /**
     * Return the ZoneId to use when formatting a {@link ZonedDateTime}
     *
     * @param input The current {@link ZoneId} from a {@link ZonedDateTime} object.
     * @return The {@link ZoneId} that should be used when formatting the {@link ZonedDateTime} object.
     */
    ZoneId getZoneId(ZoneId input);


}
