package com.AgendaDeContatos.apirest.controllers;

import com.AgendaDeContatos.apirest.Repository.ContatoRepository;
import com.AgendaDeContatos.apirest.models.Contato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contatos/")
public class ContatoController {
    @Autowired
    ContatoRepository contatoRepository;

    @CrossOrigin
    @GetMapping("/listar")
    //listar todos os contatos
    public List<Contato> listarContatos(){
        return contatoRepository.findAll();
    }

    @CrossOrigin
    @PostMapping("/salvar")
    //salvar contato
    public Contato salvarContato(@RequestBody Contato contato){
        return contatoRepository.save(contato);
    }

    @CrossOrigin
    @PutMapping("/alterar")
    //atualizar contato
    public Contato atualizarContato(@RequestBody Contato contato){
        return contatoRepository.save(contato);
    }

    @CrossOrigin
    @DeleteMapping("/excluir/{id}")
    //excluir um contato
    public void excluirContato(@PathVariable Long id){
        contatoRepository.deleteById(id);
    }

    @CrossOrigin
    @GetMapping("/getContato/{id}")
    //listar contato por id
    public Optional<Contato> getContato(@PathVariable Long id){
        return contatoRepository.findById(id);
    }
}
