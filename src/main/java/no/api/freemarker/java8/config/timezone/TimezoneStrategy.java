package no.api.freemarker.java8.config.timezone;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import no.api.freemarker.java8.config.Java8Configuration;

/**
 * Strategy the {@link Java8Configuration} uses to may transform a
 * {@link ZonedDateTime} timezone information before printing it using
 * {@code format()}.
 * 
 * @author Fritz Lumnitz
 *
 */
public interface TimezoneStrategy {

	/**
	 * Transforms the {@link ZoneId} of a {@link ZonedDateTime} into a may different
	 * one, which should be used before printing it.
	 * 
	 * @param input The {@link ZoneId} of the to print {@link ZonedDateTime}. Never
	 *              {@code null}
	 * @return The timezone which should be used to print the {@link ZonedDateTime}.
	 *         Must never be {@code null}.
	 */
	ZoneId getUpdatedZone(ZoneId input);
}
