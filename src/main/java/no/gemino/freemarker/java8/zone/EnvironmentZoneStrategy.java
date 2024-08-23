package no.gemino.freemarker.java8.zone;

import freemarker.core.Environment;

import java.time.ZoneId;
import java.util.TimeZone;

/**
 * {@link ZoneStrategy} that always will transform the input {@link ZoneId} to the {@link ZoneId} provided by
 * Environment#getCurrentEnvironment().getTimeZone().getTimeZone().<br>
 */
public class EnvironmentZoneStrategy implements ZoneStrategy {

    @Override
    public ZoneId getZoneId(ZoneId input) {
        return getZoneId();
    }

    @Override
    public ZoneId getZoneId() {
        Environment env = Environment.getCurrentEnvironment();
        if (env == null) {
            throw new IllegalStateException(getClass().getName() + " called outside of a template processing thread");
        }
        final TimeZone timeZone = env.getTimeZone();
        if (timeZone == null) {
            throw new NullPointerException("Current Environment has no TimeZone set!");
        }
        return timeZone.toZoneId();
    }
}
