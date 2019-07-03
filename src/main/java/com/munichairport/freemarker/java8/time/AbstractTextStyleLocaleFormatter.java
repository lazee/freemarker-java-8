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

package com.munichairport.freemarker.java8.time;

import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import freemarker.core.Environment;

public class AbstractTextStyleLocaleFormatter<E> extends AbstractFormatter<E> {

    public AbstractTextStyleLocaleFormatter(final E obj) {
        super(obj);
    }

    public TextStyle findTextStyle(final List list) {
        if (list.size() > 0) {
            return TextStyle.valueOf(list.get(0).toString().toUpperCase());
        }
        return TextStyle.FULL;
    }

    public Locale findLocale(final List list) {
        if (list.size() > 1) {
            return Locale.forLanguageTag(list.get(1).toString());
        }
        return Environment.getCurrentEnvironment().getLocale();
    }
}
