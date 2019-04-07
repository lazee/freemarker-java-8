package no.api.freemarker.java8.config.timezone.strategy;

import java.time.ZoneId;

import no.api.freemarker.java8.config.timezone.TimezoneStrategy;

/**
 * {@link TimezoneStrategy} which never transforms the {@link ZoneId} returning
 * always the {@code input}.<br>
 * <br>
 * The one and only instance of this singleton can be obtained by
 * {@link #INSTANCE}.
 * 
 * @author Fritz Lumnitz
 */
public enum KeepingTimezoneStrategy implements TimezoneStrategy {

	/**
	 * The one and only instance of {@link KeepingTimezoneStrategy}.
	 */
	INSTANCE;

	/**
	 * {@inheritDoc}
	 * 
	 * @return Always the {@code input}
	 */
	@Override
	public ZoneId getUpdatedZone(ZoneId input) {
		return input;
	}

}
