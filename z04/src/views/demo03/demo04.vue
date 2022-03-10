<template>

  <ul>
    <li    v-for="(item,index) in day">{{index}} == {{item.feel}} </li>
  </ul>
  <!--{{day}}-->
</template>

<script>
import { ElNotification, ElConfigProvider } from 'element-plus'

export default {
  name: 'demo04',
  data () {
    return {
      // day:JSON.parse(this.$route.params.now).data[0][1],
      day: [],
      dateThing: []
    }
  },
  beforeMount () {
    this.routeParamsCheck()
  },
  mounted () {

  },
  methods: {
    // 这里获取
    getListDay () {
      console.log(this.day.length)
      // 获取每个 day  类
      const list = []
      for (let i = 0; i < this.day.length; i++) {
        list.push(this.day[i])
      }
      // 赋值给 this.dateThing
    },

    // 如果路由 没传值过来   就提醒  (转到)
    routeParamsCheck () {
      if (this.$route.params.now === undefined) {
        ElNotification({
          title: ' date = ? ',

          message: '先选择日期',
          offset: 0
          // duration: 0,//不会自动关闭
        }) // 如果没数据 就跳到日期表

        this.$router.push({
          name: 'demo01'
        })
      } else {
        this.day = JSON.parse(this.$route.params.now).data[0]
      }
    }

  }

}
</script>

<style scoped>

</style>

<!--<template>-->

  <!--<ul>-->
    <!--<li    v-for="(item,index) in day.data[0][1]">{{index}} == {{item[1].feel}} </li>-->
  <!--</ul>-->
  <!--&lt;!&ndash;{{day}}&ndash;&gt;-->
<!--</template>-->

<!--<script>-->
  <!--import  {getThisDate} from   "@/api/wtoken"-->
  <!--import zhCn from 'element-plus/lib/locale/lang/zh-cn'-->

  <!--import { ElNotification,ElConfigProvider } from 'element-plus'-->
  <!--import {  h } from 'vue'-->

  <!--// import {getDataforday} from "@/api/demo1/http"-->
  <!--// import { reactive } from "vue";-->
  <!--export default {-->
    <!--name: "demo04",-->
    <!--data(){-->
      <!--return{-->
        <!--// day:JSON.parse(this.$route.params.now).data[0][1],-->
        <!--day:[],-->
        <!--listDay:[],//接受 day 对象 进行操作-->
        <!--notRouter:Object,-->
        <!--// dateThing: JSON.parse(this.$route.query.now) ,-->
        <!--// dateThing: JSON.parse(this.$route.params.now),-->
      <!--}-->
    <!--},-->
    <!--beforeMount(){-->
      <!--this.get();-->
      <!--console.log("赋值");-->
      <!--console.log("now"+this.$route.params.now);-->
      <!--console.log("now"+this.$route.params.now);-->

      <!--this.day = this.$route.params.now===undefined? this.notRouter:this.$route.params.now;-->
    <!--},-->
    <!--mounted(){-->
      <!--console.log(this.$route.params.now);-->

    <!--},-->
    <!--methods: {-->
      <!--//这里获取-->

      <!--get(){-->
        <!--//单击-->
        <!--// console.log(date);-->
        <!--// 这里必须请求 双击 和 单点 不能赋值-->
        <!--getThisDate("2021-12-16") //传入日期 后返回  返回全部数据-->
          <!--.then(res => {-->
            <!--// console.log(res.data);-->
            <!--if (res.data[0][1] === undefined ) {-->

              <!--ElNotification({-->
                <!--title:date + "=> no data",-->

                <!--message: "当天 无数据",-->
                <!--offset: 0,-->
                <!--// duration: 0,//不会自动关闭-->
              <!--})-->
            <!--}else{-->
              <!--this.notRouter =res;-->
            <!--}-->
          <!--});-->
      <!--},-->

      <!--getListDay(){-->
        <!--console.log(this.day.length);-->
        <!--//获取每个 day  类-->
        <!--for (let i = 0; i <this.day.length ; i++) {-->
          <!--// ListDay-->
        <!--}-->
        <!--//-->

      <!--}-->

    <!--},-->

  <!--}-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->
