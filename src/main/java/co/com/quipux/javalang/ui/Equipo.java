/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package co.com.quipux.javalang.ui;

import java.util.Calendar;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Luis Urbina
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipo {

    private Long id;
    private String text;
    private String fullname;
    private Estadio stadium;
    private Integer trophies;

    private Calendar created = Calendar.getInstance();
    private List<Jugador> plantilla;
    private List<Jugador> suplentes;

}
