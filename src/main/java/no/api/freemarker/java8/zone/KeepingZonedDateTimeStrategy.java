package no.api.freemarker.java8.zone;

import java.time.ZoneId;

/**
 * {@link ZonedDateTimeStrategy} that will never transform the {@link ZoneId} returning always the {@code input}.
 */
public class KeepingZonedDateTimeStrategy implements ZonedDateTimeStrategy {

    @Override
    public ZoneId getZoneId(ZoneId input) {
        System.out.println("yes " + input.toString());
        return input;
    }
}
