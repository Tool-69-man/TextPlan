<template>
  <div class="about">
    <h1>This is an about page</h1>
    <div id="nav" >
      <router-link  to="/demo03/pie" >demo03/pie</router-link>  |
    </div>
    <el-button  type="success">
      <router-link  to="/demo03/pie" >demo03/pie2</router-link>  |
    </el-button >
    <el-icon><document /></el-icon>

    <div>
      <router-view/>
    </div>
  </div>
</template>
