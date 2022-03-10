<template>
  <div class="about">
    <h1>This is an about page</h1>
      <div id="nav" >
        <router-link to="/demo01" >demo01</router-link>  |
        <router-link to="/demo02">demo02</router-link>  |
        <router-link to="/demo03">demo03</router-link>  |
        <router-link to="/demo04">demo04</router-link>  |
        <router-link to="/demo05">demo05</router-link>  |
        <router-link to="/demo06">demo06</router-link>  |
        <router-link to="/demo07">demo07</router-link>  |
        <router-link to="/demo08">demo08</router-link>  |
        <router-link to="/Scroll">Scroll</router-link>  |
        <router-link to="/toolTop">toolTop</router-link>  |
      </div>

      <div>
        <router-view/>
      </div>
  </div>
</template>
