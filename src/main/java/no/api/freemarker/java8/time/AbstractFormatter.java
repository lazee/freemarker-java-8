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

/**
 * Abstract formatter class.
 *
 * Adapters supporting formatters will extend this class.
 *
 * @param <E>
 *         The java.time class this formatter handles.
 */
public abstract class AbstractFormatter<E> {

    private E obj;

    public AbstractFormatter(E obj) {
        this.obj = obj;
    }

    public E getObject() {
        return obj;
    }
}
