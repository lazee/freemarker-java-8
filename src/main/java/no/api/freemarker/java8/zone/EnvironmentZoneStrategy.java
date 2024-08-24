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

import freemarker.core.Environment;

import java.time.ZoneId;
import java.util.TimeZone;

/**
 * {@link ZoneStrategy} that always will transform the input {@link ZoneId} to the {@link ZoneId} provided by
 * Environment#getCurrentEnvironment().getTimeZone().getTimeZone().<br>
 */
public class EnvironmentZoneStrategy implements ZoneStrategy {

    @Override
    public ZoneId getZoneId(ZoneId input) {
        return getZoneId();
    }

    @Override
    public ZoneId getZoneId() {
        Environment env = Environment.getCurrentEnvironment();
        if (env == null) {
            throw new IllegalStateException(getClass().getName() + " called outside of a template processing thread");
        }
        final TimeZone timeZone = env.getTimeZone();
        if (timeZone == null) {
            throw new NullPointerException("Current Environment has no TimeZone set!");
        }
        return timeZone.toZoneId();
    }
}
