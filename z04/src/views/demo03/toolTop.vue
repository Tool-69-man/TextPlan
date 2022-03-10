<template>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="vertical"
    collapse="true"
    background-color="#545c64"
    text-color="rgba(251, 240, 239, 0.91)"
    active-text-color="rgba(251,206,92,0.91)"
    @select="handleSelect"
  >
    <el-menu-item index="1" route="demo06"  v-if="menu.includes('笔记')" >
      <router-link to="/demo06"><el-icon><notebook /></el-icon></router-link>
      <template #title>Time Plans</template>
    </el-menu-item>


    <!--<el-sub-menu index="1">-->
      <!--<template #title>-->
        <!--<router-link to="/demo06"><el-icon><notebook /></el-icon></router-link>-->
      <!--</template>-->
      <!--<el-menu-item-group>-->
        <!--<template #title><span>Time Plans</span></template>-->
        <!--<el-menu-item index="1-1">Today</el-menu-item>-->

        <!--<el-menu-item index="1-2" @click="pushDemo06Date">-->
          <!--<div >-->
            <!--<el-row>-->

              <!--<el-col :span="16">-->
                <!--<el-date-picker-->
                  <!--v-model="ThisDate"-->
                  <!--type="date"-->
                  <!--placeholder="Pick a day"-->
                  <!--:disabled-date="disabledDate"-->
                  <!--:shortcuts="shortcuts"-->
                <!--&gt;-->
                <!--</el-date-picker>-->
              <!--</el-col>-->

              <!--<el-col :span="6">-->
                <!--<el-button type="success" @click="getListDay">query</el-button>-->
              <!--</el-col>-->

            <!--</el-row>-->

          <!--</div>-->
        <!--</el-menu-item>-->

      <!--</el-menu-item-group>-->

    <!--</el-sub-menu>-->











    <el-menu-item index="2" v-if="menu.includes('日历')">
      <router-link to="/demo01"> <el-icon><calendar /></el-icon></router-link>
      <template #title>Calendar</template>
    </el-menu-item>

    <el-menu-item index="3" v-if="menu.includes('饼图')">
      <router-link to="/demo02"><el-icon><histogram/></el-icon></router-link>
      <template #title>Info</template>
    </el-menu-item>

    <el-menu-item index="4"  v-if="menu.includes('登录')">
      <router-link to="/login">   <el-icon><switch-button /></el-icon>   </router-link>
      <template #title>loginOut</template>
    </el-menu-item>

    <el-menu-item index="5"  v-if="menu.includes('用户')">
      <router-link to=""> <el-icon><user /></el-icon></router-link>
      <template #title>User</template>
    </el-menu-item>

    <el-menu-item index="6"  v-if="menu.includes('管理')">
      <router-link to="/about"> <el-icon ><goblet-full/></el-icon></router-link>
      <template #title>撒(╬▔皿▔)╯</template>
    </el-menu-item>


    <el-menu-item index="7"  v-if="menu.includes('druid')">
      <a href="http://localhost/druid">D</a>
      <!--<div @click="toDruidIndex">D</div>-->
      <template #title>druid</template>
    </el-menu-item>



    <el-menu-item index="8"  v-if="menu.includes('swagger')">
      <!--<a href="http://localhost/swagger-ui.html">SW</a>-->
      <!--<a href="http://127.0.0.1:80/swagger-ui.html?">SW</a>-->

      <div @click="toSwaggerIndex">SW</div>
      <template #title>swagger-ui</template>
    </el-menu-item>



  </el-menu>





</template>

<script>
    import {ref} from 'vue';
    import {toSwaggerFun,toDruidFun} from "../../api/check";
    import { getToken } from '../../util/token'
    export default {
        name: "toolTop",
      props: [],
      data(){
          return{
            istf: 12,
            menu:["日历","笔记","管理","饼图","druid","登录","swagger","用户"],
            // menu:["日历","笔记","管理","饼图","druid","登录"],
            activeIndex:2,
            flag:false,
            // swaggerHtml:"",
          //   ThisDate:new Date(),
          //
          //   shortcuts: [
          //     {
          //       text: 'Today',
          //       value: new Date(),
          //     },
          //     {
          //       text: 'Yesterday',
          //       value: () => {
          //         const date = new Date()
          //         date.setTime(date.getTime() - 3600 * 1000 * 24)
          //         return date
          //       },
          //     },
          //     {
          //       text: 'A week ago',
          //       value: () => {
          //         const date = new Date()
          //         date.setTime(date.getTime() - 3600 * 1000 * 24 * 7)
          //         return date
          //       },
          //     },
          //   ],
          //
          }
      },


      mounted(){

      },


      methods:{

        //思路完全错了
        //这 如果用路由 跳过来 ，是不能影响 app 中 组件的
        //前者是router view 的 ， 后者 是单独的组件
         adminRole(){

          },


         toSwaggerIndex(){
           // this.$axios.get("swagger-ui.html",{
           //
           //   headers: ({
           //     //自己加的头信息全都要转成string
           //     // id: xxxx.toString(),
           //     'Authorization': getToken()+"",
           //   }),
           //
           //
           // })
              toSwaggerFun().then(res=>{
                // console.log(res.data);
                // this.swaggerHtml=res.data;
              })
         },

         toDruidIndex(){
           toDruidFun();
         }


      }

    }
</script>

<style scoped>



  .el-menu-demo{
    background-color: rgba(255, 247, 251, 0.68);
    /*filter:blur(1px);*/
    opacity: 0.6;
    position: absolute;
  }
</style>
