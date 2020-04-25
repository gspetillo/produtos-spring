package br.com.fiap.controller;

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

		model.addAttribute("produtos", repository.findAll());
		return "produtos";
	}

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
	public String findById(@PathVariable("id") long id, Model model) {
		
		model.addAttribute("produto", repository.findById(id));
		return "produto-detalhe";
	}

	@RequestMapping(value = "/produto/new", method = RequestMethod.GET)
	public String openSave() {
		return "produto-novo";
	}
	
	@RequestMapping(value = "/produto/new", method = RequestMethod.POST)
	public String save(ProdutoModel produtoModel) {
		repository.save(produtoModel);
		return "produto-novo-sucesso";
	}
	
	@RequestMapping(value = "/produto/update/{id}", method = RequestMethod.GET)
	public String openUpdate(@PathVariable("id") long id, Model model) {
		ProdutoModel produto = repository.findById(id);
		model.addAttribute("produto", produto);
		return "produto-editar";
	}
	
	@RequestMapping(value = "/produto/update", method = RequestMethod.POST)
	public String update(Model model, ProdutoModel produtoModel) {
		
		repository.update(produtoModel);
		model.addAttribute("produtos", repository.findAll());
		return "produtos";
	}
	
	@RequestMapping(value = "/produto/delete/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") long id, Model model) {
		repository.deleteById(id);
		model.addAttribute("produtos", repository.findAll());
		return "redirect:/produto";
	}
	
}
