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

package no.api.freemarker.java8.time;

import freemarker.template.TemplateModelException;
import no.api.freemarker.java8.zone.ZoneStrategy;

import java.time.ZoneId;
import java.util.List;

import static no.api.freemarker.java8.time.DateTimeTools.zoneIdLookup;

/**
 * Abstract formatter class.
 * <p>
 * Adapters supporting formatters will extend this class.
 *
 * @param <E> The <code>java.time</code> class this formatter handles.
 */
public abstract class AbstractFormatter<E> {

    private final E obj;

    private final ZoneStrategy strategy;

    public AbstractFormatter(E obj, ZoneStrategy strategy) {
        this.obj = obj;
        this.strategy = strategy;
    }

    /**
     * Get the <code>java.time</code> object that should be formated.
     *
     * @return Some <code>java.time</code> object.
     */
    public E getObject() {
        return obj;
    }

    /**
     * Get the Zone strategy to be used when formatting the object.
     *
     * @return The active zone strategy.
     */
    public ZoneStrategy getStrategy() {
        return strategy;
    }

    public ZoneId getTargetZoneId(final List argumentList, ZoneId zoneId) throws TemplateModelException {
        if (zoneId == null) {
            return zoneIdLookup(argumentList, 1).orElse(getStrategy().getZoneId());
        }
        return zoneIdLookup(argumentList, 1).orElse(getStrategy().getZoneId(zoneId));
    }
}
