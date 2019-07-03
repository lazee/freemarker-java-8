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

package no.api.freemarker.java8.config.timezone;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import no.api.freemarker.java8.config.Java8Configuration;

/**
 * Strategy the {@link Java8Configuration} uses to may transform a {@link ZonedDateTime} timezone information before printing it using {@code format()}.
 *
 * @author Fritz Lumnitz
 */
public interface TimezoneStrategy {

    /**
     * Transforms the {@link ZoneId} of a {@link ZonedDateTime} into a may different one, which should be used before printing it.
     * 
     * @param input The {@link ZoneId} of the to print {@link ZonedDateTime}. Never {@code null}
     * @return The timezone which should be used to print the {@link ZonedDateTime}. Must never be {@code null}.
     */
    ZoneId getUpdatedZone(ZoneId input);
}
