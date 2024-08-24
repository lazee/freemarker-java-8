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
 * {@link ZoneStrategy} that will never transform the {@link ZoneId} returning always the {@code input}.
 */
public class KeepingZoneStrategy implements ZoneStrategy {

    private final SystemZoneStrategy system = new SystemZoneStrategy();

    @Override
    public ZoneId getZoneId(ZoneId input) {
        return input;
    }

    @Override
    public ZoneId getZoneId() {
        return system.getZoneId();
    }
}
