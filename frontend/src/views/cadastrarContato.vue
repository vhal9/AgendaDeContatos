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
    <div class="container">
      <div class="titulo">
         <h3>Cadastrar Novo Contato</h3>
      </div>
      
      <div class="row2">
          <label>Nome</label>
          <b-form-input v-model="contato.nome" placeholder="Nome"></b-form-input>
          <label>Telefone</label>
          <b-form-input v-model="contato.telefone" placeholder="(xx)xxxxx-xxxx"></b-form-input>
          <label>Email</label>
          <b-form-input v-model="contato.email" placeholder="example@example.com"></b-form-input>
            <b-button class="button"  variant="outline-danger"  @click="limpar"> <b-icon icon="x-circle"></b-icon> Limpar</b-button>
            <b-button class="button" :disabled='isDisabled' variant="outline-success"  @click="salvar"><b-icon icon="person-plus"></b-icon> Salvar</b-button>

      </div>
      

    </div>
    
  </div>
</template>

<script>
  import ContatosServices from './../services/contatoService';

  export default{
    name: "CadastrarContato",
    data(){
      return {
        contato: {
          id:'',
          nome: '',
          email: '',
          telefone: ''
        },
        dismissSecs: 2,
        dismissCountDown: 0,
        showDismissibleAlert: false
      }
    },
    computed: {
      isDisabled: function(){
          return (this.contato.nome == '' || this.contato.email == '' || this.contato.telefone == '');
      }
    },
    methods:{
      salvar(){
        //modo novo produto
        if(this.contato.id === ''){
          ContatosServices.salvar(this.contato).then(() =>{
            this.showAlert();
            this.LimparContato();
          });
        }
      },
      LimparContato(){
        this.contato = {
          id:'',
          nome: '',
          email: '',
          telefone: ''
        };
      },
      countDownChanged(dismissCountDown) {
        this.dismissCountDown = dismissCountDown;
      },
      showAlert() {
        this.dismissCountDown = this.dismissSecs;
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
</style>