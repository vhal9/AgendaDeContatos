<template>
  <div>
    <b-navbar class="header" type="dark" variant="dark">
        <b-navbar-brand > <b-icon icon="person-circle" ></b-icon> Agenda de Contatos</b-navbar-brand>
        <b-navbar-nav>
        <b-nav-item to="/">Home</b-nav-item>
        <b-nav-item to="/cadastrar">Cadastrar Contato</b-nav-item>
        <!-- Navbar dropdowns -->
        
        </b-navbar-nav>
    </b-navbar>
</div>
</template>

<script>
export default {
    name: "Header"
}
</script>
    menu

<style scoped >
    .header{
        min-height : 80px;
    }
</style>