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

package com.munichairport.freemarker.java8.config.timezone.strategy;

import java.time.ZoneId;
import java.util.Objects;

import com.munichairport.freemarker.java8.config.timezone.TimezoneStrategy;

/**
 * {@link TimezoneStrategy} which always transforms the input {@link ZoneId} to the initial set {@link ZoneId}.
 *
 * @author Fritz Lumnitz
 */
public class StaticTimezoneStrategy implements TimezoneStrategy {

    private final ZoneId zoneId;

    private StaticTimezoneStrategy(final ZoneId zoneId) {
        assert zoneId != null : "zoneId is null";
        this.zoneId = zoneId;
    }

    /**
     * Creates a new {@link StaticTimezoneStrategy} instance, which will always return the set [{@code zone}.
     * 
     * @param zone The {@link ZoneId} which should be returned by {@link #getUpdatedZone(ZoneId)}. Must not be {@code null}
     * @return The created {@link StaticTimezoneStrategy} instance.
     */
    public static StaticTimezoneStrategy of(final ZoneId zone) {
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
    public ZoneId getUpdatedZone(final ZoneId input) {
        return this.zoneId;
    }

}
