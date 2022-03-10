









    <!--var bodyEl = document.body-->
     <!--var  content = document.querySelector( '.content-wrap' )-->
    <!--var openbtn = document.getElementById( 'open-button' )-->
     <!--var  closebtn = document.getElementById( 'close-button' )-->
     <!--var isOpen = false-->

    <!--function init() {-->
      <!--initEvents();-->
    <!--}-->

    <!--function initEvents() {-->
      <!--openbtn.addEventListener( 'click', toggleMenu );-->
      <!--if( closebtn ) {-->
        <!--closebtn.addEventListener( 'click', toggleMenu );-->
      <!--}-->

      <!--// close the menu element if the target it´s not the menu element or one of its descendants..-->
      <!--content.addEventListener( 'click', function(ev) {-->
        <!--var target = ev.target;-->
        <!--if( isOpen && target !== openbtn ) {-->
          <!--toggleMenu();-->
        <!--}-->
      <!--} );-->
    <!--}-->

    <!--function toggleMenu() {-->
      <!--if( isOpen ) {-->
        <!--classie.remove( bodyEl, 'show-menu' );-->
      <!--}-->
      <!--else {-->
        <!--classie.add( bodyEl, 'show-menu' );-->
      <!--}-->
      <!--isOpen = !isOpen;-->
    <!--}-->

    <!--init();-->

  <!--})();-->



  <!--@import "static/mytool/css/normalize.css";-->
  <!--@import "static/mytool/css/demo.css";-->
  <!--@import "static/mytool/css/menu_topside.css";-->
  <!--@import "static/mytool/fonts/font-awesome-4.2.0/css/font-awesome.min.css";-->








<template>


      <toolTop id="toolTop"> </toolTop>
       <router-view />
</template>

    <script>

      import toolTop from "./views/demo03/toolTop"
      // import login from "./views/login"

      export default {
        name: 'App',
        components: {
          toolTop,
          // login
        },


      }
    </script>


<style>
  body html{
    margin:0;
  }

  #toolTop{
    z-index: 10000;
  }

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
   -webkit-font-smoothing: antialiased;
   -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
