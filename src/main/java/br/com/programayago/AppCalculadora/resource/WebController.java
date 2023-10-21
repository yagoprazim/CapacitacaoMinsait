package br.com.programayago.AppCalculadora.resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {
    @GetMapping("/")
    public String showForm(Model model){
        //adicionar uma lista de operações (calculadora)
        model.addAttribute("modelOperacoes", List.of("Soma","Subtracao"));
        return "form";
    }
    @PostMapping("/")
    public String handleFormSubmission(@RequestParam String modelOperacoes, @RequestParam String valor01, @RequestParam String valor02, Model model){
        if(modelOperacoes.isEmpty() || valor01.isEmpty() || valor02.isEmpty()){
            return null;
        }

        String resposta = "";
        int resp = 0;
        switch (modelOperacoes) {
            case "Soma":
                resp = Integer.parseInt(valor01) + Integer.parseInt(valor02);
                resposta = String.valueOf(resp);
                break;
            case "Subtracao":
                resp = Integer.parseInt(valor01) - Integer.parseInt(valor02);
                resposta = String.valueOf(resp);
                break;
        }
        resposta = String.valueOf(resp);

        //devolver a resposta para a tela (isso é o que estamos devolvendo):
        model.addAttribute("modelOperacoes", List.of("Soma","Subtracao"));
        model.addAttribute("response", resposta);
        model.addAttribute("selectedModel", modelOperacoes);
        return "form";
    }

}
