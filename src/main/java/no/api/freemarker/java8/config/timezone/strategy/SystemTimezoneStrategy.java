/*
 * Copyright (c) 2015-2015 Amedia Utvikling AS.
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
