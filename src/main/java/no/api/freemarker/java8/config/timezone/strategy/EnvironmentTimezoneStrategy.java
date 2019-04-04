package no.api.freemarker.java8.config.timezone.strategy;

import java.time.ZoneId;
import java.util.TimeZone;

import freemarker.core.Environment;
import no.api.freemarker.java8.config.timezone.TimezoneStrategy;

public enum EnvironmentTimezoneStrategy implements TimezoneStrategy {

	INSTANCE;

	@Override
	public ZoneId getUpdatedZone(ZoneId input) {
		final Environment currentEnvironment = Environment.getCurrentEnvironment();
		if (currentEnvironment == null)
			throw new IllegalStateException(getClass().getName() + " called outside of a TemplateProcessin Thread");
		final TimeZone currentTimezone = currentEnvironment.getTimeZone();
		if (currentTimezone == null)
			throw new NullPointerException("Current Environment has no TimeZone set!");
		return currentTimezone.toZoneId();
	}

}
