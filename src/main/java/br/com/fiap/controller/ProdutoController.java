package br.com.fiap.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.fiap.model.ProdutoModel;
import br.com.fiap.repository.ProdutoRepository;

@Controller
public class ProdutoController {

	ProdutoRepository repository = new ProdutoRepository();

	// Busca
	@RequestMapping(value = "/produto", method = RequestMethod.GET)
	public String findAll(Model model) {

		ArrayList<ProdutoModel> produtos = repository.findAll();
		model.addAttribute("produtos", produtos);
		return "produtos";
	}

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
	public String findById(@PathVariable("id") long id, Model model) {
		
		ProdutoModel produto = repository.findById(id);
		model.addAttribute("produto", produto);
		return "produto-detalhe";
	}

}
