<template>
  <div id="app">
    <b-alert variant="success"
      :show="dismissCountDown"
      dismissible
      @dismissed="dismissCountDown=0"
      @dismiss-count-down="countDownChanged"
    >
      Cadastrado com sucesso
    </b-alert>
    <!-- formulario para cadastrar contato -->
    <div class="container">
      <div class="titulo">
         <h3>Cadastrar Novo Contato</h3>
      </div>
      
      <div class="row">
        <div class="col-md-5">
          <label>Nome</label>
          <b-form-input v-model="contato.nome" placeholder="Nome" required pattern="/^\([1-9]{2}\)[0-9]{4,5}-[0-9]{4}$/"></b-form-input>
        </div>
        <div class="col-md-3">
          <label>Telefone</label>
          <b-form-input v-model="contato.telefone" placeholder="xxxxxxxxx"></b-form-input>
        </div>
        <div class="col-md-4">
          <label>Email</label>
          <b-form-input v-model="contato.email" placeholder="example@example.com"></b-form-input>
        </div>
      </div>
      <div class="row">
        <div class="col-md-3">
          <label>CEP</label>
          <b-form-input v-model="contato.cep" placeholder="00000-000"></b-form-input>
        </div>
        <div class="col-md-1">
          <label>UF</label>
          <b-form-input :disabled="enderecoDisabled"  v-model="contato.estado"></b-form-input>
        </div>
        <div class="col-md-4">
          <label>Cidade</label>
          <b-form-input :disabled="true" v-model="contato.cidade" ></b-form-input>
        </div>
        <div class="col-md-4">
          <label>Bairro</label>
          <b-form-input v-model="contato.bairro" ></b-form-input>
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>Complemento</label>
          <b-form-input v-model="contato.complemento"></b-form-input>
        </div>
        <div class="col-md-6">
          <label>Rua</label>
          <b-form-input v-model="contato.rua"></b-form-input>
        </div>
        <div class="col-md-2">
          <label>Número</label>
          <b-form-input type="number" v-model="contato.numero" min="1"></b-form-input>
        </div>
      </div>
    
        <div class="botoes">
            <b-button class="button"  variant="outline-danger"  @click="LimparContato"> <b-icon icon="x-circle"></b-icon> Limpar</b-button>
            <b-button class="button" :disabled='isDisabled' variant="outline-success"  @click="salvar"><b-icon icon="person-plus"></b-icon> Salvar</b-button>
        </div>
    </div>
      
    
  </div>
</template>

<script>
  import ContatosServices from './../services/contatoService';
  import CepServices from './../services/cepService';
  export default{
    name: "CadastrarContato",
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
        dismissSecs: 2,
        dismissCountDown: 0,
        showDismissibleAlert: false
      }
    },
    computed: {
      //desabilitar o botão de salvar
      isDisabled: function(){
          return this.desabilitarBotaoSalvar();
      },
      //preencher os dados do endereço a partir do cep
      enderecoDisabled: function(){
        return this.preencherEndereco();
      },
    },
    methods:{
      //salvar os dados do contato
      salvar(){
        //modo novo produto
        if(this.contato.id === ''){
          ContatosServices.salvar(JSON.parse(JSON.stringify(this.contato)))
          .then(() =>{
            this.showAlert();
            this.LimparContato();
          });
        }
      },
      //limpar os dados do contato
      LimparContato(){
        this.contato = {
          id:'',
          nome: '',
          email: '',
          telefone: '',
          complemento:'',
          rua:'',
          numero:'',
          cep:'',
          estado:'',
          cidade:''
        };

      },
      //tempo para fechar o modal de confirmação de salvamento do contato
      countDownChanged(dismissCountDown) {
        this.dismissCountDown = dismissCountDown;
      },
      showAlert() {
        this.dismissCountDown = this.dismissSecs;
      },
      //buscar o endereço pelo cep
      buscarEnderecoPorCep(cep){
        CepServices.getEndereco(cep).then(resposta =>{
          this.contato.estado = resposta.data.uf;
          this.contato.cidade = resposta.data.localidade;
          this.contato.bairro = resposta.data.bairro;
          this.contato.rua = resposta.data.logradouro;
        })
        .catch(()=> {
          this.contato.estado = "";
          this.contato.cidade = "";
          this.contato.bairro = "";
          this.contato.rua = "";
        });
      },
      //desabilita o botão de salvar
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
      //verifica se o cep é do tamanho certo para realizar a busca do endereço
      preencherEndereco(){
        if(this.contato.cep === '' || this.contato.cep.length < 8){
          return true;
        }
        else{
          this.buscarEnderecoPorCep(this.contato.cep);
          return true;
        }
      }
      

    },

    
  };
</script>

<style scoped>
  .titulo{
    margin-top: 1%;
    margin-bottom: 1%;
  }
  .button{
    margin-left: 10px;
  }
  .botoes{
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
  }
</style>