package no.api.freemarker.java8.config.timezone.strategy;

import java.time.ZoneId;

import no.api.freemarker.java8.config.timezone.TimezoneStrategy;

public enum KeepingTimezoneStrategy implements TimezoneStrategy {

	INSTANCE;
	
	@Override
	public ZoneId getUpdatedZone(ZoneId input) {
		return input;
	}

}
