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

package no.api.freemarker.java8.time;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.Map;

public class MethodNameToTemporalUnitMapper {

    private static final String PLUS_SECONDS = "plusSeconds";
    private static final String PLUS_MINUTES = "plusMinutes";
    private static final String PLUS_HOURS = "plusHours";
    private static final String PLUS_DAYS = "plusDays";
    private static final String PLUS_WEEKS = "plusWeeks";
    private static final String PLUS_MONTHS = "plusMonths";
    private static final String PLUS_YEARS = "plusYears";

    private static final Map<String, ChronoUnit> METHOD_TO_UNIT_MAP = new HashMap<>();

    static {
        METHOD_TO_UNIT_MAP.put(PLUS_SECONDS, ChronoUnit.SECONDS);
        METHOD_TO_UNIT_MAP.put(PLUS_MINUTES, ChronoUnit.MINUTES);
        METHOD_TO_UNIT_MAP.put(PLUS_HOURS, ChronoUnit.HOURS);
        METHOD_TO_UNIT_MAP.put(PLUS_DAYS, ChronoUnit.DAYS);
        METHOD_TO_UNIT_MAP.put(PLUS_WEEKS, ChronoUnit.WEEKS);
        METHOD_TO_UNIT_MAP.put(PLUS_MONTHS, ChronoUnit.MONTHS);
        METHOD_TO_UNIT_MAP.put(PLUS_YEARS, ChronoUnit.YEARS);
    }

    public TemporalUnit map(String methodName) {
        return METHOD_TO_UNIT_MAP.get(methodName);
    }
}