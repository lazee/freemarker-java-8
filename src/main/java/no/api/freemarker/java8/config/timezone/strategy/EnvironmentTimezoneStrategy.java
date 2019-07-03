/*
 * Copyright (c) 2019 Flughafen München GmbH.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package no.api.freemarker.java8.config.timezone.strategy;

import java.time.ZoneId;
import java.util.TimeZone;

import freemarker.core.Environment;
import no.api.freemarker.java8.config.timezone.TimezoneStrategy;

/**
 * {@link TimezoneStrategy} which always transforms the input {@link ZoneId} to
 * the {@link ZoneId} provided by
 * {@link Environment#getCurrentEnvironment()}{@link Environment#getTimeZone()
 * .getTimeZone()}.<br>
 * <br>
 * The one and only instance of this singleton can be obtained by
 * {@link #INSTANCE}.
 * 
 * @author Fritz Lumnitz
 *
 */
public enum EnvironmentTimezoneStrategy implements TimezoneStrategy {

	/**
	 * The one and only instance of {@link EnvironmentTimezoneStrategy}.
	 */
	INSTANCE;

	/**
	 * {@inheritDoc}
	 * 
	 * Must always be called inside a template processing tread. Otherwise a
	 * {@link IllegalStateException} is thrown.
	 * 
	 * @see Environment#getCurrentEnvironment()
	 */
	@Override
	public ZoneId getUpdatedZone(ZoneId input) {
		final Environment currentEnvironment = Environment.getCurrentEnvironment();
		if (currentEnvironment == null)
			throw new IllegalStateException(getClass().getName() + " called outside of a template processing thread");
		final TimeZone currentTimezone = currentEnvironment.getTimeZone();
		if (currentTimezone == null)
			throw new NullPointerException("Current Environment has no TimeZone set!");
		return currentTimezone.toZoneId();
	}

}
