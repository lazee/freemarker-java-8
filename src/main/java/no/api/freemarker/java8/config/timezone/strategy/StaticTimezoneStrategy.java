package no.api.freemarker.java8.config.timezone.strategy;

import java.time.ZoneId;
import java.util.Objects;

import no.api.freemarker.java8.config.timezone.TimezoneStrategy;

/**
 * {@link TimezoneStrategy} which always transforms the input {@link ZoneId} to
 * the initial set {@link ZoneId}.
 * 
 * @author Fritz Lumnitz
 *
 */
public class StaticTimezoneStrategy implements TimezoneStrategy {

	private final ZoneId zoneId;

	private StaticTimezoneStrategy(ZoneId zoneId) {
		assert zoneId != null : "zoneId is null";
		this.zoneId = zoneId;
	}

	/**
	 * Creates a new {@link StaticTimezoneStrategy} instance, which will always
	 * return the set [{@code zone}.
	 * 
	 * @param zone The {@link ZoneId} which should be returned by
	 *             {@link #getUpdatedZone(ZoneId)}. Must not be {@code null}
	 * @return The created {@link StaticTimezoneStrategy} instance.
	 */
	public static StaticTimezoneStrategy of(ZoneId zone) {
		Objects.requireNonNull(zone, "zone");
		return new StaticTimezoneStrategy(zone);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return The initial set {@link ZoneId}.
	 * @see #of(ZoneId)
	 */
	@Override
	public ZoneId getUpdatedZone(ZoneId input) {
		return this.zoneId;
	}

}
