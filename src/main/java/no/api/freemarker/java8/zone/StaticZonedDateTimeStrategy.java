package no.api.freemarker.java8.zone;

import java.time.ZoneId;

/**
 * {@link ZonedDateTimeStrategy} that always will transform the input {@link ZoneId} to the initial set {@link ZoneId}.
 */
public class StaticZonedDateTimeStrategy implements ZonedDateTimeStrategy {

    private final ZoneId zoneId;

    /**
     * Creates a new {@link StaticZonedDateTimeStrategy} instance, that will always return the given [{@code zone}.
     *
     * @param zoneId The {@link ZoneId} that should be returned by {@link #getZoneId(ZoneId)}.
     * @return New instance of {@link StaticZonedDateTimeStrategy}.
     */
    public StaticZonedDateTimeStrategy(ZoneId zoneId) {
        if (zoneId == null) {
            throw new NullPointerException("provided zoneId is null");
        }
        this.zoneId = zoneId;
    }

    @Override
    public ZoneId getZoneId(ZoneId input) {
        return this.zoneId;
    }
}
