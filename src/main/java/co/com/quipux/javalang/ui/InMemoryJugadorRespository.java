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

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * @author Luis Urbina
 */
public class InMemoryJugadorRespository implements JugadorRepository {

    private static AtomicLong counter = new AtomicLong();

    private final ConcurrentMap<Long, Jugador> messages = new ConcurrentHashMap<Long, Jugador>();

    @Override
    public Iterable<Jugador> findAll() {
	return this.messages.values();
    }

    @Override
    public Jugador save(Jugador message) {
	Long id = message.getId();
	if (id == null) {
	    id = counter.incrementAndGet();
	    message.setId(id);
	}
	this.messages.put(id, message);
	return message;
    }

    @Override
    public Jugador findJugador(Long id) {
	return this.messages.get(id);
    }

    @Override
    public List<Jugador> findByIdIn(List<Long> ids) {
	return this.messages.values().stream().filter(j -> ids.contains(j.getId())).collect(Collectors.toList());
    }

}
