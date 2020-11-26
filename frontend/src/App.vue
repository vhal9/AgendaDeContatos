<template>
  <div>
    <Header />
    <router-view />
  </div>
</template>

<script>
  import Header from './components/header';
  export default{
    name: "App",
    components: {
      Header,
    },
  };

</script>

<style>
  .modal{
    background-color:transparent !important;
    max-height: 100%;
  }
</style>