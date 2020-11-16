package com.AgendaDeContatos.apirest.Repository;

import com.AgendaDeContatos.apirest.models.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
