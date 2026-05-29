package br.com.fatec.catalogo.controllers;

import br.com.fatec.catalogo.models.CategoriaModel;
import br.com.fatec.catalogo.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/novo")
    @PreAuthorize("hasRole('ADMIN')") 
    public String exibirForm(Model model) {
        model.addAttribute("categoria", new CategoriaModel());
        return "cadastro-categoria";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid CategoriaModel categoria, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) return "cadastro-categoria";
        categoriaService.salvar(categoria);
        String horario = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        redirectAttributes.addFlashAttribute("mensagemSucesso",
                "Categoria cadastrada com sucesso às " + horario + ".");
        return "redirect:/produtos";
    }
}
