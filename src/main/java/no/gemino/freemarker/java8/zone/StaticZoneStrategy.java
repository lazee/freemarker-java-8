package no.gemino.freemarker.java8.zone;

import java.time.ZoneId;

/**
 * {@link ZoneStrategy} that always will transform the input {@link ZoneId} to the initial set {@link ZoneId}.
 */
public class StaticZoneStrategy implements ZoneStrategy {

    private final ZoneId zoneId;

    /**
     * Creates a new {@link StaticZoneStrategy} instance, that will always return the given [{@code zone}.
     *
     * @param zoneId The {@link ZoneId} that should be returned by {@link #getZoneId(ZoneId)}.
     */
    public StaticZoneStrategy(ZoneId zoneId) {
        if (zoneId == null) {
            throw new NullPointerException("provided zoneId is null");
        }
        this.zoneId = zoneId;
    }

    @Override
    public ZoneId getZoneId(ZoneId input) {
        return getZoneId();
    }

    @Override
    public ZoneId getZoneId() {
        return this.zoneId;
    }
}
