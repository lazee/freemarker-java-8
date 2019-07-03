/*
 * Copyright (c) 2019 Flughafen München GmbH.
 *
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
 *
 * This file was modified by Flughafen München GmbH in order to add
 * or change the following functionality:
 *  - Migrated enum implementation from ExtFormatStyle to own class
 *    ExtFormatStyle only represents an interface now
 */

package com.munichairport.freemarker.java8.time;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public enum PreparedFormatStyle implements ExtFormatStyle {
    LONG_DATE(true, false, FormatStyle.LONG),
    LONG_DATETIME(true, true, FormatStyle.LONG),
    LONG_TIME(false, true, FormatStyle.LONG),
    MEDIUM_DATE(true, false, FormatStyle.MEDIUM),
    MEDIUM_DATETIME(true, true, FormatStyle.MEDIUM),
    MEDIUM_TIME(false, true, FormatStyle.MEDIUM),
    SHORT_DATE(true, false, FormatStyle.SHORT),
    SHORT_DATETIME(true, true, FormatStyle.SHORT),
    SHORT_TIME(false, true, FormatStyle.SHORT);

    private final DateTimeFormatter formatter;

    PreparedFormatStyle(final boolean withDate, final boolean withTime, final FormatStyle formatStyle) {
        if (withDate && withTime) {
            this.formatter = DateTimeFormatter.ofLocalizedDateTime(formatStyle);
        } else if (withDate) {
            this.formatter = DateTimeFormatter.ofLocalizedDate(formatStyle);
        } else {
            this.formatter = DateTimeFormatter.ofLocalizedTime(formatStyle);
        }
    }

    @Override
    public DateTimeFormatter getFormatter() {
        return this.formatter;
    }
}
