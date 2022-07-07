/*
 * Copyright (c) 2015-2022 Jakob Vad Nielsen
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

package no.api.freemarker.java8;

import freemarker.template.*;
import no.api.freemarker.java8.time.*;
import no.api.freemarker.java8.zone.KeepingZoneStrategy;
import no.api.freemarker.java8.zone.ZoneStrategy;
import no.api.freemarker.java8.zone.ZonedDateTimeStrategy;

import java.time.*;
import java.time.temporal.Temporal;

/**
 * Freemarker ObjectWrapper that extends the DefaultObjectWrapper with support for all classes in the new java.time api.
 */
public class Java8ObjectWrapper extends DefaultObjectWrapper {

    private ZoneStrategy strategy;

    public Java8ObjectWrapper(Version incompatibleImprovements) {
        super(incompatibleImprovements);
        this.strategy = new KeepingZoneStrategy();
    }

    public Java8ObjectWrapper(Version incompatibleImprovements, ZoneStrategy strategy) {
        super(incompatibleImprovements);
        this.strategy = strategy;
    }

    /**
     * @deprecated Use {@link Java8ObjectWrapper#setZoneStrategy(ZoneStrategy)} instead.
     * @param strategy The strategy to be used.
     */
    @Deprecated
    public void setZonedDateTimeStrategy(ZonedDateTimeStrategy strategy) {
        this.strategy = strategy;
    }

    public void setZoneStrategy(ZoneStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    protected TemplateModel handleUnknownType(Object obj) throws TemplateModelException {
        TemplateModel delegate = _handleUnknownType(obj);
        return obj instanceof Temporal && delegate instanceof TemplateHashModel
              ? new TemporalDialerAdapter((Temporal) obj, this, (TemplateHashModel) delegate, strategy)
              : delegate;
    }

    private TemplateModel _handleUnknownType(Object obj) throws TemplateModelException {
        if (obj instanceof Clock) {
            return new ClockAdapter((Clock) obj, this, strategy);
        } else if (obj instanceof Duration) {
            return new DurationAdapter((Duration) obj, this, strategy);
        } else if (obj instanceof Instant) {
            return new InstantAdapter((Instant) obj, this, strategy);
        } else if (obj instanceof LocalDate) {
            return new LocalDateAdapter((LocalDate) obj, this, strategy);
        } else if (obj instanceof LocalDateTime) {
            return new LocalDateTimeAdapter((LocalDateTime) obj, this, strategy);
        } else if (obj instanceof LocalTime) {
            return new LocalTimeAdapter((LocalTime) obj, this, strategy);
        } else if (obj instanceof MonthDay) {
            return new MonthDayAdapter((MonthDay) obj, this, strategy);
        } else if (obj instanceof OffsetDateTime) {
            return new OffsetDateTimeAdapter((OffsetDateTime) obj, this, strategy);
        } else if (obj instanceof OffsetTime) {
            return new OffsetTimeAdapter((OffsetTime) obj, this, strategy);
        } else if (obj instanceof Period) {
            return new PeriodAdapter((Period) obj, this, strategy);
        } else if (obj instanceof Year) {
            return new YearAdapter((Year) obj, this, strategy);
        } else if (obj instanceof YearMonth) {
            return new YearMonthAdapter((YearMonth) obj, this, strategy);
        } else if (obj instanceof ZonedDateTime) {
            return new ZonedDateTimeAdapter((ZonedDateTime) obj, this, strategy);
        } else if (obj instanceof ZoneOffset) {
            return new ZoneOffsetAdapter((ZoneOffset) obj, this, strategy);
        } else if (obj instanceof ZoneId) {
            return new ZoneIdAdapter((ZoneId) obj, this, strategy);
        }
        return super.handleUnknownType(obj);
    }
}