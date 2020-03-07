package no.api.freemarker.java8.zone;

import java.time.ZoneId;

/**
 * {@link ZonedDateTimeStrategy} that always will transform the input {@link ZoneId} to the {@link ZoneId} provided by
 * {@link ZoneId#systemDefault()}.<br>
 */
public class SystemZonedDateTimeStrategy implements ZonedDateTimeStrategy {

    @Override
    public ZoneId getZoneId(ZoneId input) {
        return ZoneId.systemDefault();
    }
}
