package no.api.freemarker.java8.config.timezone.strategy;

import java.time.ZoneId;

import no.api.freemarker.java8.config.timezone.TimezoneStrategy;

/**
 * {@link TimezoneStrategy} which always transforms the input {@link ZoneId} to
 * the {@link ZoneId} provided by
 * {@link ZoneId#systemDefault()}.<br>
 * <br>
 * The one and only instance of this singleton can be obtained by
 * {@link #INSTANCE}.
 * 
 * @author Fritz Lumnitz
 *
 */
public enum SystemTimezoneStrategy implements TimezoneStrategy {

	/**
	 * The one and only instance of {@link SystemTimezoneStrategy}
	 */
	INSTANCE;
	
	/**
	 * {@inheritDoc}
	 * @return Always the {@link ZoneId#systemDefault()}
	 */
	@Override
	public ZoneId getUpdatedZone(ZoneId input) {
		return ZoneId.systemDefault();
	}

}
