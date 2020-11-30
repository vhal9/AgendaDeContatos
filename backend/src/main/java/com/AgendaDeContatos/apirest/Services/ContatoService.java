package com.AgendaDeContatos.apirest.Services;

import com.AgendaDeContatos.apirest.Repository.ContatoRepository;
import com.AgendaDeContatos.apirest.models.Contato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {
    @Autowired
    ContatoRepository contatoRepository;

    public List<Contato> listarContatos(){
        return contatoRepository.findAll();
    }
    public Contato salvarContato(Contato contato){
        return contatoRepository.save(contato);
    }
    public Contato atualizarContato(Contato contato){
        return contatoRepository.save(contato);
    }
    public void excluirContato(Long id){
        contatoRepository.deleteById(id);
    }
    public Contato getContato(Long id){
        return contatoRepository.findFirstById(id);
    }



}
