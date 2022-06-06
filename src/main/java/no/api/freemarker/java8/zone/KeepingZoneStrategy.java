package no.api.freemarker.java8.zone;

import java.time.ZoneId;

/**
 * {@link ZoneStrategy} that will never transform the {@link ZoneId} returning always the {@code input}.
 */
public class KeepingZoneStrategy implements ZoneStrategy {

    private final SystemZoneStrategy system = new SystemZoneStrategy();

    @Override
    public ZoneId getZoneId(ZoneId input) {
        return input;
    }

    @Override
    public ZoneId getZoneId() {
        return system.getZoneId();
    }
}
