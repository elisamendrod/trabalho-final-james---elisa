package br.com.fatec.catalogo.controllers;

import br.com.fatec.catalogo.models.UsuarioModel;
import br.com.fatec.catalogo.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService; 

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        
        model.addAttribute("usuario", new UsuarioModel());
        return "cadastro-usuario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid UsuarioModel usuario, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "cadastro-usuario";
        }

        
        
        usuarioService.salvar(usuario);

        String horario = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        redirectAttributes.addFlashAttribute("mensagemSucesso",
                "Usuário cadastrado com sucesso às " + horario + ".");

        return "redirect:/produtos";
    }
}
