package com.AgendaDeContatos.apirest.controller;

import com.AgendaDeContatos.apirest.Services.ContatoService;
import com.AgendaDeContatos.apirest.controllers.ContatoController;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import com.AgendaDeContatos.apirest.models.Contato;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest
public class ContatoControllerTest {
    @Autowired
    private ContatoController contatoController;

    @MockBean
    private ContatoService contatoService;

    @BeforeEach
    public void setup(){
        standaloneSetup(this.contatoController);
    }

    @Test
    public void deveRetornarSucesso_QuandoBuscarContato(){
        Mockito.when(this.contatoService.getContato(1L)).thenReturn(new Contato(1L, "Carlos", "carlos@gmail.com", "33524086", "Rua Chagas", "", 34,"27197000", "Palmares","Rio das Flores", "RJ"));
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/api/contatos/getContato/{id}", 1L)
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test void deveRetornarNaoEncontrado_QuandoBuscarContato(){
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/api/contatos/getContato/{id}", 5L)
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornarSucesso_QuandoBuscarTodosContatos(){
        List<Contato> contatos = new ArrayList<>();
        contatos.add(new Contato(1L, "Carlos", "carlos@gmail.com", "33524086", "Rua Chagas", "", 34,"27197000", "Palmares","Rio das Flores", "RJ"));
        Mockito.when(this.contatoService.listarContatos()).thenReturn(contatos);
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/api/contatos/", 1L)
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test void deveRetornarNaoEncontrado_QuandoBuscarTodosContatos(){
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/api/contatos/")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornarSucesso_QuandoSalvarContato(){
        Contato contato = new Contato(1L, "Carlos", "carlos@gmail.com", "33524086", "Rua Chagas", "", 34,"27197000", "Palmares","Rio das Flores", "RJ");
        Mockito.when(this.contatoService.salvarContato(contato)).thenReturn(contato);
        given()
                .accept(ContentType.JSON)
        .when()
                .post("/api/contatos/salvar", contato)
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test void deveRetornarNaoEncontrado_QuandoSalvarContatoInvalido(){
        Contato contato = new Contato(1L, "Carlos", "carlos@gmail.com", "33524086", "Rua Chagas", "", 34,"27197000", "Palmares","Rio das Flores", "RJ");
        Mockito.when(this.contatoService.salvarContato(contato)).thenReturn(new Contato());
        given()
                .accept(ContentType.JSON)
        .when()
                .post("/api/contatos/salvar", new Contato(1L, "Carlos", "", "teste", "Rua Chagas", "", 34,"27197000", "Palmares","Rio das Flores", "RJ"))
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    public void deveRetornarSucesso_QuandoAtualizarContato(){
        Contato contato = new Contato(1L, "Carlos", "carlos@gmail.com", "33524086", "Rua Chagas", "", 34,"27197000", "Palmares","Rio das Flores", "RJ");
        Mockito.when(this.contatoService.atualizarContato(contato)).thenReturn(contato);
        given()
                .accept(ContentType.JSON)
        .when()
                .put("/api/contatos/alterar", new Contato(1L, "Carlos", "carlos@gmail.com", "33524086", "Rua Chagas", "", 34,"27197000", "Palmares","Rio das Flores", "RJ"))
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test void deveRetornarNaoEncontrado_QuandoAtualizarContatoInvalido(){
        Contato contato = new Contato(1L, "Carlos", "carlos@gmail.com", "33524086", "Rua Chagas", "", 34,"27197000", "Palmares","Rio das Flores", "RJ");
        Mockito.when(this.contatoService.atualizarContato(contato)).thenReturn(new Contato());
        given()
                .accept(ContentType.JSON)
        .when()
                .put("/api/contatos/alterar", new Contato(1L, "Carlos", "", "teste", "Rua Chagas", "", 34,"27197000", "Palmares","Rio das Flores", "RJ"))
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
    @Test void deveRetornarNaoEncontrado_QuandoAtualizarContatoInexistente(){
        Contato contato = new Contato(1L, "Carlos", "carlos@gmail.com", "33524086", "Rua Chagas", "", 34,"27197000", "Palmares","Rio das Flores", "RJ");
        Mockito.when(this.contatoService.atualizarContato(contato)).thenReturn(new Contato());
        given()
                .accept(ContentType.JSON)
        .when()
                .put("/api/contatos/alterar", new Contato(1L, "Carlos", "", "teste", "Rua Chagas", "", 34,"27197000", "Palmares","Rio das Flores", "RJ"))
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
    @Test
    public void deveRetornarSucesso_QuandoExcluirContato(){
        Mockito.when(this.contatoService.excluirContato(1L)).thenReturn(true);
        given()
                .accept(ContentType.JSON)
        .when()
                .delete("/api/contatos/excluir/{id}", 1L)
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test void deveRetornarNaoEncontrado_QuandoExcluirContatoInexistente(){
        Mockito.when(this.contatoService.excluirContato(1L)).thenReturn(false);
        given()
                .accept(ContentType.JSON)
                .when()
                .delete("/api/contatos/excluir/{id}", 1L)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }


}
