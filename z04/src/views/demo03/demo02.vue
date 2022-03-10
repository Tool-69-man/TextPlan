<template>

<div id="backfilter">

</div>

    <div id="card_pies">
    <ElScrollbar>
     <el-carousel  @change="changeMessage" direction="horizontal" :interval="4000" type="card" height="400px">
    <!--<el-carousel-item v-for="item in listnum" :key="item">-->
      <!--<h3 class="medium">{{ item }}</h3>-->
      <!--这里显示echarts-->

      <!--<div>-->
        <!--<div id="nav" >-->
          <!--<router-link  :to="to" >demo03/{{item}}</router-link>  |-->
        <!--</div>-->
        <!--<div>-->
          <!--<router-view/>-->
        <!--</div>-->
      <!--</div>-->

      <!--<HelloWorld/>-->
      <!--<pie id="pie"/>-->
      <!--<demo01/>-->
      <!--<demo03/>-->
      <!--<demo04/>-->

    <!--</el-carousel-item>-->
    <el-carousel-item>
      <pie1  class="pie"/>

    </el-carousel-item>
    <el-carousel-item>
      <pie2 class="pie"/>

    </el-carousel-item>

    <el-carousel-item>
     <pie  :nowdate="nowdate" class="pie"/>
    </el-carousel-item>

    <!--<el-carousel-item>-->
      <!--<pie2/>c-->
    <!--</el-carousel-item>-->

  </el-carousel>
    </ElScrollbar>

      <el-divider>
      </el-divider>

      <div id="pieTextDemo">

        <!--<div id="left" >选择时间范围组件</div>-->
        <!--<div id="center">{{message}}</div>-->
        <!--<div id="right" >选择事件</div>-->

        <el-container>

          <el-aside width="300px"> <div id="left" >选择时间范围组件</div></el-aside>
          <el-container>
            <el-aside width="600px"> <div id="center">{{message}}</div></el-aside>
            <el-main>  <div id="right" >选择事件</div></el-main>

          </el-container>
        </el-container>




      </div>

    </div>




</template>

<script>

import pie from './pie/pie.vue'
import pie1 from './pie/piea.vue'
import pie2 from './pie/pieb.vue'
import {ElScrollbar } from 'element-plus'
export default {
  name: 'demo02',
  components: {
    pie,
    pie1,
    pie2

  },
  data () {
    return {
      nowdate: '2021-12-16',
      message:"",
      messageList:[]
    }
  },
  mounted(){
    this.init();
  },

  methods:{
    init(){
      let  list=["这个事件在一周占比 name=日期 ，value = 一天含有的时间","所有事件一周占比，name=事件，value=时间","今天的事件"]
      this.messageList=list;
      this.message=this.messageList[0];
    },


    changeMessage(index,pre){
      // alert(index);//轮播图 当前item
      this.message=this.messageList[index];
    }


  }


}

</script>

<style scoped>

  *{
    overflow: hidden;
    margin: 0;
  }


  body{
    overflow: hidden;
    margin: 0;

  }
   .el-carousel{
     background-color: rgba(227, 234, 237, 0.85);

   }

    .pie{
      /*width: 100px;*/
      /*position: fixed;*/
      position: absolute;
      /*position: static;*/
      top: 20%;
      left: 25%;
      right: 25%;
      bottom: 20%;
    }

  .el-carousel__item h3 {
    color: #d32011;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
    text-align: center;
  }

  .el-carousel__item{
    /*position: absolute;*/
    border: 10px solid rgba(206, 195, 200, 0.53);
    /*margin-top: 30px;*/
    /*margin-left: 100px;*/
  }

  .el-carousel__item:nth-child(2n) {
    background-color: rgba(212, 231, 234, 0.9);
  }

  .el-carousel__item:nth-child(2n + 1) {
    background-color: rgba(241, 242, 240, 0.9);
  }
  #card_pies{
    overflow: hidden;
    /*position:fixed;*/
    z-index: -1;
    margin: 0;
    padding: 0;
    /*top: 0%;*/
  }






  #right  {


  }
   #center{


   }

   #left{


   }


   main.el-main {
     padding:0;
     height: 200px;
   }

   #pieTextDemo {
     z-index: 1;
     position: fixed;


     margin: 0;
     padding: 0;

     background-color: rgba(207, 202, 225, 0.63);
     height: inherit;


     width: 100%;
     border-color: #ffecb3;
     color: #424242;
     font-size: 18px;
     font-family: Monaco;
     line-height: 24px;

     opacity: 0.8;
   }


  #backfilter{
    position: absolute;
    left: 0;
    top: 0;
    width:100%;
    height:100%;
    filter: blur(20px);
    z-index: 1;
    /*background-color: #cecbc8;*/
    /*background-color: rgb(226, 196, 159);*/
    /*background-color: rgb(183, 215, 226);*/
    /*background-color: rgb(183, 215, 226);*/
    background: linear-gradient(20deg, rgba(226, 182, 168, 0.98), rgba(199, 219, 243, 0.84), rgb(183, 215, 226));

    /*background-color: rgba(226, 182, 168, 0.98);*/
  }

</style>
