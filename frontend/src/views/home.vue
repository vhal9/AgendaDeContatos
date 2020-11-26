<template>
  <div id="app">    
    <div class="container">
      <div class="titulo">
         <h3>Lista de Contatos</h3>
      </div>
      <!-- tabela com os dados dos contatos -->
      <div class="table-container">
        <b-table class="table" show-empty striped hover :items="contatosList" :fields="fields" >
          <template #cell(actions)="row">
            <b-button size="sm" @click="visualizar(row.item)" class="mr-1">
              <b-icon icon="info-circle"> </b-icon>
            </b-button>
            <b-button size="sm" @click="editar(row.item)" class="mr-1">
              <b-icon icon="pencil-square"> </b-icon>
            </b-button>
            <b-button size="sm" @click="remover(row.item)" class="mr-1">
              <b-icon icon="trash"> </b-icon>
            </b-button>
          </template>

        </b-table>
      </div>
    </div>
    <!-- modal de visualização dos dados de um contato -->
    <div class="modal">
      <b-modal id="visualizacao-modal"  ref="visualizacao-modal" title="Informações de Contato" hide-footer >
        <div class="row1">
            <label class="label1">ID: </label>
            <label class="label2">{{this.contatoVisualizacao.id}}</label>
          
        </div>
        <div class="row1">
          <label class="label1">Nome:</label>
          <label class="label2">{{this.contatoVisualizacao.nome}}</label>
          
        </div>
        <div class="row1">
          <label class="label1"> Telefone: </label>
          <label class="label2">{{this.contatoVisualizacao.telefone}}</label>
        
        </div>
        <div class="row1">
          <label class="label1">Email:  </label>
          <label class="label2">{{this.contatoVisualizacao.email}}</label>
        </div>
        <div class="row1">
          <div class="row1">
            <label class="label1">CEP:</label>
            <label class="label2">{{this.contatoVisualizacao.cep}}</label>
          </div>
          <div class="row1 row3">
              <label class="label1">UF:</label>
              <label class="label2">{{this.contatoVisualizacao.estado}}</label>
          </div>
        </div>
        <div class="row1">
          <label class="label1">Cidade:</label>
          <label class="label2">{{this.contatoVisualizacao.cidade}}</label>
        </div>
        <div class="row1">
          <label class="label1">Rua:</label>
          <label class="label2">{{this.contatoVisualizacao.rua}}</label>
        </div>
        <div class="row1">
          <div class="row1">
            <label class="label1">Bairro:</label>
            <label class="label2">{{this.contatoVisualizacao.bairro}}</label>
          </div>
          <div class="row1 row3">
            <label class="label1">Número:</label>
            <label class="label2">{{this.contatoVisualizacao.numero}}</label>
          </div>

        </div>
        
      </b-modal>
    </div>
    <!-- modal de edição dos dados de um contato -->
    <div class="modal">
      <b-modal id="edicao-modal" size="xl" ref="edicao-modal" title="Editando Contato" hide-footer >
        <div class="row">
          <h4>ID: {{this.contato.id}}</h4><br>
        </div>
        
        <div class="row">
          <div class="col-md-5">
            <label>Nome</label>
            <b-form-input v-model="contato.nome" placeholder="Nome"></b-form-input>
          </div>
        
          <div class="col-md-3">
              <label>Telefone</label>
              <b-form-input v-model="contato.telefone" placeholder="XXXXXXXXX"></b-form-input>
          </div>
          <div class="col-md-4">
              <label>Email</label>
              <b-form-input v-model="contato.email" placeholder="example@example.com"></b-form-input>
          </div>
        </div>
        <div class="row">
          <div class="col-md-5">
            <label>CEP</label>
            <b-form-input v-model="contato.cep" placeholder="00000000"></b-form-input>
          </div>
          <div class="col-md-1">
            <label>UF</label>
            <b-form-input :disabled="enderecoDisabled" v-model="contato.estado" ></b-form-input>
          </div>
          <div class="col-md-3">
            <label>Cidade</label>
            <b-form-input :disabled="true" v-model="contato.cidade" ></b-form-input>
          </div>
          <div class="col-md-3">
            <label>Bairro</label>
            <b-form-input v-model="contato.bairro" ></b-form-input>
          </div>
        </div>
        <div class="row">
          <div class="col-md-4">
            <label>Complemeto</label>
            <b-form-input v-model="contato.complemento" ></b-form-input>
          </div>
        
          <div class="col-md-6">
              <label>Rua</label>
              <b-form-input v-model="contato.rua" ></b-form-input>
          </div>
          <div class="col-md-2">
              <label>Número</label>
              <b-form-input type="number" min="1" v-model="contato.numero" ></b-form-input>
          </div>
        </div>
          <div class="row2">
            <b-button class="button-modal"  variant="outline-danger"  @click="hideModalEdicao"> <b-icon icon="x-circle"></b-icon> Cancelar</b-button>
            <b-button class="button-modal" :disabled='isDisabled' variant="outline-success"  @click="salvar"><b-icon icon="person-check"></b-icon> Salvar</b-button>
          </div>
      </b-modal>
    </div>
    <!-- modal de confirmação de exclusão de um contato -->
    <div class="modal">
      <b-modal id="confirmarRemocao" ref="confirmarRemocao" title="Excluindo Contato" hide-footer>
        <p>Deseja realmente excluir o contato {{contato.nome}}?  </p>

        <b-button class="button-modal"  variant="outline-success"  @click="hideModalDelecao"> Não</b-button>
        <b-button class="button-modal" variant="outline-success"  @click="confirmarRemover"> Sim</b-button>
      </b-modal>
    </div>
      
  </div>
</template>

<script>
  import ContatosServices from './../services/contatoService';
  import CepServices from './../services/cepService';
  const clone = require('lodash.clone');

  export default{
    name: "Home",
    data(){
      return {
        contato: {
          id:'',
          nome: '',
          email: '',
          telefone: '',
          complemento:'',
          rua:'',
          numero:'',
          cep:'',
          estado:'',
          cidade:'',
          bairro:''
        },
        contatoVisualizacao: {
          id:'',
          nome: '',
          email: '',
          telefone: '',
          complemento:'',
          rua:'',
          numero:'',
          cep:'',
          estado:'',
          cidade:'',
          bairro:''
        },
        
        contatosList: [],
        fields: [{key:'nome', sortable:'true'}, {key:'telefone', sortable:'true'},{key: 'email', sortable:'true'}, { key: 'actions', label: 'Actions' }],
      }
    },
    computed: {
      //verifica se os dados estão preenchidos para habilitar o botão de salvar
      isDisabled: function(){
        return this.desabilitarBotaoSalvar();
      },
      //realizar o preenchimento do endereço com os dados trazidos do cep
      enderecoDisabled: function(){
        return this.preencherEndereco();
      },
    },
    
    methods:{
      //listar todos os contatos do back
      listar(){
        ContatosServices.listar().then(resposta => {
          this.contatosList = resposta.data
        })
      },
      //abrir e preencher  a modal de edição
      editar(contatoEdicao){
        this.contato = clone(contatoEdicao);
        this.$bvModal.show('edicao-modal');
      },
      //abrir e preencher  a modal de visualização
      visualizar(contatoVisualizacao){
        this.contatoVisualizacao = clone(contatoVisualizacao);
        this.$bvModal.show('visualizacao-modal');
      },
      //salvar o contato em edição
      salvar(){
        ContatosServices.atualizar(JSON.parse(JSON.stringify(this.contato))).then(()=>{
          this.listar();
          this.hideModalEdicao();
        })
      },
      //abrir o modal de confirmação de exclusão de um contato
      remover(contato){
        this.contato = clone(contato);
        this.$bvModal.show('confirmarRemocao');
      },
      // excluir um contato
      confirmarRemover(){
        ContatosServices.deletar(this.contato.id).then(()=> {
          this.listar();
          this.hideModalDelecao();
        })
      },
      // buscar os dados de um endereço a partir do cep
      buscarEnderecoPorCep(cep){
        CepServices.getEndereco(cep).then(resposta =>{
          this.contato.estado = resposta.data.uf;
          this.contato.cidade = resposta.data.localidade;
          if(this.resposta.data.bairro !== " ") this.contato.bairro = resposta.data.bairro;
          if(this.resposta.data.rua !== " ") this.contato.rua = resposta.data.logradouro;
        });
      },
      //fechar o modal de edição
      hideModalEdicao() {
        this.$refs['edicao-modal'].hide();
      },
      //fechar o modal de confirmação de exclusão
      hideModalDelecao() {
        this.$refs['confirmarRemocao'].hide();
      },
      //retorna true se os campos do contato estão vazios
      desabilitarBotaoSalvar(){
        if(this.contato.nome === '') return true;
        if(this.contato.telefone === '') return true;
        if(this.contato.email === '') return true;
        if(this.contato.cep === '') return true;
        if(this.contato.estado === '') return true;
        if(this.contato.cidade === '') return true;
        if(this.contato.bairro === '') return true;
        if(this.contato.rua === '') return true;
        if(this.contato.numero === '') return true;
        return false;

      },
      //verifica se o cep está preenchido para realizar a busca do endereço
      preencherEndereco(){
        if(this.contato.cep === '' || this.contato.cep.length < 8){
          return true;
        }
        else{
          this.buscarEnderecoPorCep(this.contato.cep);
          return true;
        }
      },
      
    },

    mounted(){
      this.listar();
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

    .row{
      margin-left: 1%;
      display: flex;
      justify-content: space-between;
    }
    .row1{
      display: flex;
      flex-direction: row;
      
    }
    .row2{
      margin-left: 1%;
      display: flex;
      justify-content: flex-end;
    }
    .row3{
      margin-left:20%;
    }
    
    .label1{
      font-size: 20px;
      color: black;
    }
   .label2{
      font-size: 20px;
      color: #555353;
      margin-left: 1%;
    }

</style>