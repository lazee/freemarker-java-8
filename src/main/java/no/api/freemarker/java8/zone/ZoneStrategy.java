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
import java.time.ZonedDateTime;

/**
 * Defines the general strategy used when dealing with zones in different parts of the DateTime API.
 */
public interface ZoneStrategy {

    /**
     * Return the ZoneId to use for formatting a java.time object.
     *
     * @param input The current {@link ZoneId} provided by a java.time object or method parameter.
     * @return The {@link ZoneId} that should be used for formatting the {@link ZonedDateTime} object.
     */
    ZoneId getZoneId(ZoneId input);

    /**
     * Return the ZoneId to use for formatting a java.time object, when no zone information exists on object and are
     * not given as a parameter.
     *
     * @return The {@link ZoneId} that should be used for formatting the object.
     */
    ZoneId getZoneId();
}
