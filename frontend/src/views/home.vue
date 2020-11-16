<template>
  <div id="app">    
    <div class="container">
      <div class="titulo">
         <h3>Lista de Contatos</h3>
      </div>
      
      <div class="table-container">
        <b-table class="table" show-empty striped hover :items="contatosList" :fields="fields" >
          <template #cell(actions)="row">
            <b-button size="sm" @click="editar(row.item)" class="mr-1">
              <b-icon icon="pencil-square"> </b-icon>
            </b-button>
            <b-button size="sm" @click="remover(row.item)" class="mr-1">
              <b-icon icon="trash"> </b-icon>
            </b-button>
          </template>

        </b-table>
      </div>

    <div class="modal">
      <b-modal id="edicao-modal"  ref="edicao-modal" title="Editando Contato" hide-footer >
        <div class="row2">
          <h4>ID: {{this.contato.id}}</h4><br>
          <label>Nome</label>
          <b-form-input v-model="contato.nome" placeholder="Nome"></b-form-input>
          <label>Telefone</label>
          <b-form-input v-model="contato.telefone" placeholder="(XX)XXXXX-XXXX"></b-form-input>
          <label>Email</label>
          <b-form-input v-model="contato.email" placeholder="example@example.com"></b-form-input>
            <b-button class="button-modal"  variant="outline-danger"  @click="hideModalEdicao"> <b-icon icon="x-circle"></b-icon> Cancelar</b-button>
            <b-button class="button-modal" :disabled='isDisabled' variant="outline-success"  @click="salvar"><b-icon icon="person-check"></b-icon> Salvar</b-button>
        </div>
      </b-modal>
      <b-modal id="confirmarRemocao" ref="confirmarRemocao" title="Excluinbdo Contato" hide-footer>
        <p>Deseja realmente excluir o contato {{contato.nome}}?  </p>

        <b-button class="button-modal"  variant="outline-success"  @click="hideModalDelecao"> Não</b-button>
        <b-button class="button-modal" variant="outline-success"  @click="confirmarRemover"> Sim</b-button>
      </b-modal>
    </div>
      
      

    </div>
    
  </div>
</template>

<script>
  import ContatosServices from './../services/contatoService';
  const clone = require('lodash.clone');

  export default{
    name: "Home",
    data(){
      return {
        contato: {
          id:'',
          nome: '',
          email: '',
          telefone: ''
        },
        contatosList: [],
        fields: [{key:'nome', sortable:'true'}, {key:'telefone', sortable:'true'},{key: 'email', sortable:'true'}, { key: 'actions', label: 'Actions' }],
      }
    },
    computed: {
      isDisabled: function(){
          return (this.contato.nome == '' || this.contato.email == '' || this.contato.telefone == '');
      }
    },
    mounted(){
      this.listar();
    },
    methods:{
      listar(){
        ContatosServices.listar().then(resposta => {
          this.contatosList = resposta.data
        })
      },
      editar(contatoEdicao){

        this.contato = clone(contatoEdicao);
        this.$bvModal.show('edicao-modal');
      },
      salvar(){
        ContatosServices.atualizar(this.contato).then(()=>{
          this.listar();
          this.hideModalEdicao();
        })
      },
      remover(contato){
        this.contato = clone(contato);
        this.$bvModal.show('confirmarRemocao');
      },
      confirmarRemover(){
        ContatosServices.deletar(this.contato.id).then(()=> {
          this.listar();
          this.hideModalDelecao();
        })
       
      },
      hideModalEdicao() {
        this.$refs['edicao-modal'].hide();
      },
       hideModalDelecao() {
        this.$refs['confirmarRemocao'].hide();
      },
    },
    
  };
</script>
<style scoped >
    .titulo{
        margin-top: 1%;
        margin-bottom: 1%;
    }
    .table-container{
      min-width: 98%;
      width: 98%;
    }
    .button-modal{
      margin-left: 10px;
    }
</style>