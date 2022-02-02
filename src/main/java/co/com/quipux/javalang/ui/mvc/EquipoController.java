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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.com.quipux.javalang.ui.Equipo;
import co.com.quipux.javalang.ui.EquipoRepository;
import co.com.quipux.javalang.ui.JugadorRepository;

/**
 * @author Luis Urbina
 */
@Controller
@RequestMapping("/equipos")
public class EquipoController {
    private final EquipoRepository messageRepository;
    private final JugadorRepository jugadorRepository;

    @Autowired
    public EquipoController(EquipoRepository messageRepository, JugadorRepository jugadorRepository) {
	this.messageRepository = messageRepository;
	this.jugadorRepository = jugadorRepository;
    }

    @RequestMapping
    public ModelAndView list() {
	Iterable<Equipo> messages = this.messageRepository.findAll();
	return new ModelAndView("equipos/list", "equipos", messages);
    }

    @RequestMapping("{id}")
    public ModelAndView view(@PathVariable("id") Equipo message, Model model) {
	model.addAttribute("equipo", message);
	model.addAttribute("allPlayers", jugadorRepository.findAll());
	return new ModelAndView("equipos/view", model.asMap());
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(@ModelAttribute Equipo message, Model model) {
	model.addAttribute("allPlayers", jugadorRepository.findAll());
	return "equipos/form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView create(@Valid Equipo message, BindingResult result, RedirectAttributes redirect) {
	if (result.hasErrors()) {
	    return new ModelAndView("equipos/form", "formErrors", result.getAllErrors());
	}
	message = this.messageRepository.save(message);
	redirect.addFlashAttribute("globalMessage", "Successfully created a new equipo");
	return new ModelAndView("redirect:/equipos/{equipo.id}", "equipo.id", message.getId());
    }

    @RequestMapping("foo")
    public String foo() {
	throw new RuntimeException("Expected exception in controller");
    }

}
