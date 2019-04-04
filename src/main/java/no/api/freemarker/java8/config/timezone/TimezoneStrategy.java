package no.api.freemarker.java8.config.timezone;

import java.time.ZoneId;

public interface TimezoneStrategy {

	ZoneId getUpdatedZone(ZoneId input);
}
