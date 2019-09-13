package com.eventosapp.controllers;

import java.util.ArrayList;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventosapp.models.Convidado;
import com.eventosapp.models.Evento;
import com.eventosapp.models.Usuario;
import com.eventosapp.repository.ConvidadoRepository;
import com.eventosapp.repository.EventoRepository;
import com.eventosapp.repository.UsuarioRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private ConvidadoRepository convidadoRepository;
		
	@GetMapping("/cadastrarUsuario")
	public String cadastrarUsuario() {
		return "cadastrarUsuario";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping(value = "/")
	public String index() {
		return "home";
	}

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
	public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarEvento";

		}

		eventoRepository.save(evento);

		attributes.addFlashAttribute("mensagem", "Evento adicionado com sucesso!");

		return "redirect:/cadastrarEvento";
	}

	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView modelandView = new ModelAndView("evento/eventos");
		Iterable<Evento> eventos = eventoRepository.findAll();
		modelandView.addObject("eventos", eventos);
		return modelandView;

	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalheEvento(@PathVariable("codigo") long codigo) {
		Evento evento = eventoRepository.findByCodigo(codigo);
		ModelAndView modelandView = new ModelAndView("evento/detalheEvento");
		modelandView.addObject("evento", evento);

		Iterable<Convidado> convidado = convidadoRepository.findByEvento(evento);

		modelandView.addObject("convidado", convidado);
		return modelandView;
	}

	@RequestMapping("/deletarEvento")
	public String deletarEvento(long codigo) {
		Evento evento = eventoRepository.findByCodigo(codigo);
		eventoRepository.delete(evento);
		return "redirect:/eventos";
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
	public String detalheEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado,
			BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{codigo}";
		}
		Evento evento = eventoRepository.findByCodigo(codigo);
		convidado.setEvento(evento);
		convidadoRepository.save(convidado);
		attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
		return "redirect:/{codigo}";
	}

	@RequestMapping(value = "editarEvento{codigo}", method = RequestMethod.GET)
	public ModelAndView editarEvento(@PathVariable("codigo") long codigo) {// no pathvariable uso o id que coloquei no
																			// html na grade editar <td><a
																			// th:href="@{/editarpessoa/{idpessoa}(idpessoa=${pessoa.id})}">Editar</a></td>
		Evento evento = eventoRepository.findByCodigo(codigo);// O findById retona um Optional.
		ModelAndView modelAndView = new ModelAndView("evento/editarEvento");
		modelAndView.addObject("evento", evento);

		return modelAndView;

	}

	@RequestMapping(value = "editarEvento{codigo}", method = RequestMethod.POST)
	public String editarEventos(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Dados não alterados!");
			return "redirect:/eventos";

		}

		eventoRepository.save(evento);

		attributes.addFlashAttribute("mensagem", "Dados alterados com sucesso!!");

		return "redirect:/eventos";

	}
	
	@RequestMapping("/deletarConvidado")
	public String deletarConvidado(String rg) {
		Convidado convidado = convidadoRepository.findByRg(rg);
		convidadoRepository.delete(convidado);

		Evento evento = convidado.getEvento();
		long codigoLong = evento.getCodigo();
		String codigo = "" + (codigoLong);
		return "redirect:/" + codigo;
	}
}
