package no.api.freemarker.java8.config.timezone.strategy;

import java.time.ZoneId;
import java.util.Objects;

import no.api.freemarker.java8.config.timezone.TimezoneStrategy;

public class StaticTimezoneStrategy implements TimezoneStrategy {

	private final ZoneId zoneId;
	
	private StaticTimezoneStrategy(ZoneId zoneId) {
		assert zoneId != null : "zoneId is null";
		this.zoneId = zoneId;
	}


	public static StaticTimezoneStrategy of(ZoneId zone) {
		Objects.requireNonNull(zone, "zone");
		return new StaticTimezoneStrategy(zone);
	}

	@Override
	public ZoneId getUpdatedZone(ZoneId input) {
		return this.zoneId;
	}

}
