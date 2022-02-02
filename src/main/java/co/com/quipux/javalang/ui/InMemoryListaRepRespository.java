/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.com.quipux.javalang.ui;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Luis Urbina
 */
public class InMemoryListaRepRespository implements ListaRepRepository {

    private static AtomicLong counter = new AtomicLong();

    private final ConcurrentMap<Long, ListaReproduccion> messages = new ConcurrentHashMap<Long, ListaReproduccion>();

    @Override
    public Iterable<ListaReproduccion> findAll() {
	return this.messages.values();
    }

    @Override
    public ListaReproduccion save(ListaReproduccion message) {
	Long id = message.getId();
	if (id == null) {
	    id = counter.incrementAndGet();
	    message.setId(id);
	}
	this.messages.put(id, message);
	return message;
    }

    @Override
    public ListaReproduccion findListaReproduccion(Long id) {
	return this.messages.get(id);
    }

    @Override
    public ListaReproduccion findByNombre(String name) {
	return this.messages.values().stream().filter(l -> l.getNombre().equalsIgnoreCase(name)).findFirst()
		.orElse(null);
    }

    @Override
    public Long deleteByNombre(String name) {
	ListaReproduccion toDelete = this.messages.values().stream().filter(l -> l.getNombre().equalsIgnoreCase(name))
		.findFirst().orElse(null);
	if (toDelete != null)
	    return this.messages.remove(toDelete.getId()).getId();
	return null;
    }

}
