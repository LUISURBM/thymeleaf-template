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

package co.com.quipux.javalang.ui.mvc;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.quipux.javalang.ui.Equipo;
import co.com.quipux.javalang.ui.ListaRepRepository;
import co.com.quipux.javalang.ui.ListaReproduccion;

/**
 * @author Luis Urbina
 */
@Controller
@RequestMapping("/lista-reproduccion")
public class ListaRepController {
    private final ListaRepRepository listaRepRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public ListaRepController(ListaRepRepository messageRepository) {
	this.listaRepRepository = messageRepository;
    }

    @RequestMapping(value = "{nombre}", method = RequestMethod.GET)
    public ResponseEntity<String> view(@PathVariable("nombre") String nombre) throws JsonProcessingException {
	return new ResponseEntity<String>(mapper.writeValueAsString(listaRepRepository.findByNombre(nombre)),
		HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> viewAll() throws JsonProcessingException {
	return new ResponseEntity<String>(mapper.writeValueAsString(listaRepRepository.findAll()), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public String createForm(@PathVariable String message, Model model) {
	model.addAttribute("allPlayers", listaRepRepository.deleteByNombre(message));
	return "equipos/form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> create(@Valid ListaReproduccion message, BindingResult result,
	    RedirectAttributes redirect) {
	if (result.hasErrors()) {
	    return new ResponseEntity<String>("Creado", HttpStatus.CREATED);
	}
	message = this.listaRepRepository.save(message);
	return new ResponseEntity<String>("Creado", HttpStatus.CREATED);
    }

}
