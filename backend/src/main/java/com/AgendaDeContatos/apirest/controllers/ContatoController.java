package com.AgendaDeContatos.apirest.controllers;

import com.AgendaDeContatos.apirest.Services.ContatoService;
import com.AgendaDeContatos.apirest.models.Contato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contatos/")
public class ContatoController {
    @Autowired
    ContatoService contatoService;

    @CrossOrigin
    @GetMapping("/listar")
    //listar todos os contatos
    public ResponseEntity<List> listarContatos(){
        return new ResponseEntity<>(contatoService.listarContatos(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/salvar")
    //salvar contato
    public ResponseEntity<Contato> salvarContato(@RequestBody Contato contato){
        return new ResponseEntity<>(contatoService.salvarContato(contato), HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/alterar")
    //atualizar contato
    public ResponseEntity<Contato> atualizarContato(@RequestBody Contato contato){
        return new ResponseEntity<>(contatoService.atualizarContato(contato), HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/excluir/{id}")
    //excluir um contato
    public ResponseEntity<Boolean> excluirContato(@PathVariable Long id){
        contatoService.excluirContato(id);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/getContato/{id}")
    //listar contato por id
    public ResponseEntity<Contato> getContato(@PathVariable Long id){
        return new ResponseEntity<>(contatoService.getContato(id),HttpStatus.OK);
    }
}
