/*
 * Copyright (c) 2015-2024 Jakob Vad Nielsen
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

package no.api.freemarker.java8.zone;

import java.time.ZoneId;

/**
 * {@link ZoneStrategy} that always will transform the input {@link ZoneId} to the initial set {@link ZoneId}.
 */
public class StaticZoneStrategy implements ZoneStrategy {

    private final ZoneId zoneId;

    /**
     * Creates a new {@link StaticZoneStrategy} instance, that will always return the given [{@code zone}.
     *
     * @param zoneId The {@link ZoneId} that should be returned by {@link #getZoneId(ZoneId)}.
     */
    public StaticZoneStrategy(ZoneId zoneId) {
        if (zoneId == null) {
            throw new NullPointerException("provided zoneId is null");
        }
        this.zoneId = zoneId;
    }

    @Override
    public ZoneId getZoneId(ZoneId input) {
        return getZoneId();
    }

    @Override
    public ZoneId getZoneId() {
        return this.zoneId;
    }
}
