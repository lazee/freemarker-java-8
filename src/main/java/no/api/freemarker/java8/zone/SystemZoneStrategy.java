package no.api.freemarker.java8.zone;

import java.time.ZoneId;

/**
 * {@link ZoneStrategy} that always will transform the input {@link ZoneId} to the {@link ZoneId} provided by
 * {@link ZoneId#systemDefault()}.
 */
public class SystemZoneStrategy implements ZoneStrategy {

    @Override
    public ZoneId getZoneId(ZoneId input) {
        return getZoneId();
    }

    @Override
    public ZoneId getZoneId() {
        return ZoneId.systemDefault();
    }
}
