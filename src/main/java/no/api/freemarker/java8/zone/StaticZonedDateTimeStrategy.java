package no.api.freemarker.java8.zone;

import java.time.ZoneId;

/**
 * @deprecated Use {@link StaticZoneStrategy} instead.
 */
@Deprecated
public class StaticZonedDateTimeStrategy extends StaticZoneStrategy {

    public StaticZonedDateTimeStrategy(ZoneId zoneId) {
        super(zoneId);
    }
}
