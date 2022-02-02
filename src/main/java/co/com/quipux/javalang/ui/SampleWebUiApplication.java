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

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SampleWebUiApplication {

    @Bean
    public EquipoRepository equipoRepository() {
	return new InMemoryEquipoRespository();
    }

    @Bean
    public JugadorRepository jugadorRepository() {
	InMemoryJugadorRespository toReturn = new InMemoryJugadorRespository();
	toReturn.save(Jugador.builder().nombres("Quipux 1").edad(11).posicion(Posicion.DEFENSA)
		.created(Calendar.getInstance()).build());
	toReturn.save(Jugador.builder().nombres("Quipux 2").edad(12).posicion(Posicion.DEFENSA)
		.created(Calendar.getInstance()).build());
	toReturn.save(Jugador.builder().nombres("Quipux 3").edad(12).posicion(Posicion.DELANTERO)
		.created(Calendar.getInstance()).build());
	toReturn.save(Jugador.builder().nombres("Quipux 4").edad(13).posicion(Posicion.DEFENSA)
		.created(Calendar.getInstance()).build());
	toReturn.save(Jugador.builder().nombres("Quipux 5").edad(14).posicion(Posicion.PORTERO)
		.created(Calendar.getInstance()).build());
	toReturn.save(Jugador.builder().nombres("Quipux 6").edad(15).posicion(Posicion.MEDIO)
		.created(Calendar.getInstance()).build());
	toReturn.save(Jugador.builder().nombres("Quipux 7").edad(16).posicion(Posicion.MEDIO)
		.created(Calendar.getInstance()).build());
	return toReturn;
    }

    @Bean
    public Converter<String, Equipo> messageConverter() {
	return new Converter<String, Equipo>() {
	    @Override
	    public Equipo convert(String id) {
		return equipoRepository().findEquipo(Long.valueOf(id));
	    }
	};
    }

    @Bean
    public Converter<String, Jugador> jugadorConverter() {
	return new Converter<String, Jugador>() {
	    @Override
	    public Jugador convert(String id) {
		return jugadorRepository().findJugador(Long.valueOf(id));
	    }
	};
    }

    @Bean
    public Converter<String[], List<Jugador>> jugadoresConverter() {
	return new Converter<String[], List<Jugador>>() {
	    @Override
	    public List<Jugador> convert(String[] ids) {
		return jugadorRepository()
			.findByIdIn(Arrays.asList(ids).stream().map(Long::valueOf).collect(Collectors.toList()))
			.stream().collect(Collectors.toList());
	    }
	};
    }

    @Bean
    public ListaRepRepository listaRepRepository() {
	return new InMemoryListaRepRespository();
    }

    public static void main(String[] args) throws Exception {
	SpringApplication.run(SampleWebUiApplication.class, args);
    }

}
