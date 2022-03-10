<template>
  <!--<el-result title="404" sub-title="Sorry, request error">-->
    <!--<template #icon>-->


    <!--</template>-->
    <!--<template #extra>-->
      <!--&lt;!&ndash;<el-button type="primary">Back</el-button>&ndash;&gt;-->
      <!--&lt;!&ndash;<router-link to="/"> </router-link>&ndash;&gt;-->
    <!--</template>-->
  <!--</el-result>-->
  <!--asdadasdsd-->

  <img  id="img404" alt="404" src="../../assets/404.jpg"/>
  <p id="text404">404</p>
</template>

<script>

  // import from "../../assets/404.jpg"
    export default {
        name: "notfound.vue"
    }
</script>

<style scoped>
    #img404{
      z-index: -1;
      width: 70%;
      height: 70%;
    }

  #text404{
    position: absolute;

    z-index: 10;
  }

    @import url(https://fonts.googleapis.com/css2?family=Merriweather:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap);

    #text404 {
      text-align: center;
      left:50%;
      font-family: Merriweather;
      font-size: 96px;
      top: 50%;
      color: #e0e0e0;
    }

    #img404 {
      padding-left: 121px;


    }


</style>
