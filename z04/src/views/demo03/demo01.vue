<template>
  <el-config-provider :locale="locale">

    <div class="background--custom">
    <el-calendar v-model="value"  class="mycalendar"  >
      <template #dateCell="{ data }"   >
        <div class="magnify"  >

          <el-button  round id="cp" :class="data.isSelected ? 'is-selected' : ''"  @click="show(data.day)"  @dblclick=" allcalendar(data.day)"  >
            <!--{{ data.day.split('-').slice(1).join('-') }}-->
            {{this.$dayjs(data.day).format("MM-DD")}}
          </el-button>
        </div>
      </template >

    </el-calendar >
    </div>
  </el-config-provider>
</template>

<script>

import { getThisDate } from '@/api/wtoken'
// 引入vue方法
// import {  } from 'element-plus'
// 中文包
import zhCn from 'element-plus/lib/locale/lang/zh-cn'

import { ElNotification, ElConfigProvider } from 'element-plus'
import { h, ref, reactive } from 'vue'

export default {
  name: 'demo01',
  components: {
    [ElConfigProvider.name]: ElConfigProvider
  },
  data () {
    return {
      value: new Date(), // 时间的值
      fontSize: 'extra-large', // el 设的 字体大小  改了没用 直接 css 改的
      locale: zhCn // 组件中文化 <el-config-provider>
    }
  },
  components: {

  },

  methods: {
    allcalendar (date) {
      // alert(date) // 这里我获取 我选中 的日期 （那就在 组件 获取redis数值 路由设置值）
      // 最好这里简短的预览 事件 后 选择 跳转

      getThisDate(date) // 传入日期
        .then(res => {
          if (res.data[res.data.length-1].length === 0) {
            ElNotification({
              title: date + '=> no data',

              message: '当天 无数据',
              offset: 0,
              // duration: 0,//不会自动关闭
            }) // 如果没数据就不跳

            this.$router.push({
              name: 'demo06',
              params: {
                choiceDate: date,
              }
            });



          } else {
            // alert("怎么不跳了")  //不要在单击事件中设置弹窗
            this.$router.push({
              // name: "demo04",
              // name: "demo07",
              name: 'demo06',
              // name:"demo08",
              params: {
                choiceDate: date,
                now: JSON.stringify(res)
              }

            })
          }
        })
    },
    show (date) {
      // 单击
      // console.log(date);
      // 这里必须请求 双击 和 单点 不能赋值
      getThisDate(date) // 传入日期 后返回  返回全部数据
        .then(res => {

          let newRedisDatalen =res.data.length-1;

          // if (res.data[0].length === 0) {
          if (res.data[newRedisDatalen].length === 0) {


            ElNotification({
              title: date + '=> no data',

              // message: "当天 无数据",
              offset: 0,
              // duration: 0,//不会自动关闭
              dangerouslyUseHTMLString: true,
              // message: '<image  href="../../../public/Hippopx.jpg" style="width: auto"/>',
              message: '<img  src="../../../favicon.ico" style="width: auto"/>'
              // message: '<div style="width: auto;height: auto;background-color: chocolate"></div>',

            })
          } else {
            console.log(('有数据输出'))
            const list = [] // 用vue day 接受 数组  会转化成奇怪的类型
            for (let i = 0; i < res.data[newRedisDatalen].length; i++) {


              if (res.data[newRedisDatalen][i].text===""||res.data[newRedisDatalen][i].text===null){
                list.push(res.data[newRedisDatalen][i].thing.name+" > ")
              } else{
                list.push(res.data[newRedisDatalen][i].text + " > ")
              }

            }
            this.elnoti(date, list)
          }
        })

      // .then(res => {
      //       console.log(res);
      //       for (let i = 0; i < res.length; i++) {
      //         // this.day[i]=(result[i].text);
      //         this.day.push(res[i].text+"\t\n");
      //       }
      //       this.elnoti(data);
      //       this.day=[];
      //       res=null;
      //     });

      // console.log("before"+this.day);
      // console.log("after"+this.day);
    },
    elnoti (date, list) {


      ElNotification({
        title: date,
        message: h('i', { style: 'color: teal' }, list),
        offset: 1,
        // duration: 0// 不会自动关闭

      })
    }

  }
}
</script>

<style>

  div.el-calendar__body {
    padding-top: 0px;
    border-right-width: 10px;
    padding-right: 0px;
    padding-left: 0px;
    margin-top: 1px;
    border-bottom-width: 10px;
    padding-bottom: 10px

  }

  .is-selected{
    /*background: linear-gradient(80deg, #ffb295, #feffb7, #d9ffbe);*/
    /*background-color: rgb(59, 103, 231);*/
    box-shadow: 10px 10px 5px #c599ff;
    /*position: fixed;*/
    /*width: 10px;*/
    border-radius: 25px;
    /*color: cornflowerblue;*/

  }

  .mycalendar{
    width: 80%;
    height: 90%;
    /*background: linear-gradient(90deg, #a765f5, #6db0e7, #f5ec92);*/
    /*border: 25px solid green;*/
    padding: 0;
    margin: 0;
    /*background: linear-gradient(80deg, rgba(25, 182, 45, 0.35), rgba(182, 169, 21, 0.35), rgba(33, 22, 168, 0.35));*/
    /*background: linear-gradient(rgba(93, 95, 101, 0.37), rgba(139, 142, 148, 0.4), rgba(123, 126, 131, 0.32));*/
    background: linear-gradient(rgba(214, 220, 220, 0.84), rgba(216, 197, 197, 0.4), rgba(233, 255, 248, 0.72));
    /*background: linear-gradient(30deg, #a765f5, #6db0e7, #f5ec92);*/

    position:fixed;
    /*animation: gradient2 1s alternate infinite;*/

    top: 10%;
    bottom: 10%;
    left: 10%;
    right: 10%;

  }
  div.el-notification.right{
    background: linear-gradient(20deg, rgba(255, 246, 116, 0.75), rgba(228, 171, 134, 0.84), rgba(221, 97, 96, 0.71));
    color: #eceff1;
    padding-right: 0px

  }

  .mycalendar  :hover{
    /*background: linear-gradient(50deg, #a765f5, #6db0e7, #f5ec92);*/
    /*background: linear-gradient(70deg, rgba(122, 91, 195, 0.88), rgba(77, 190, 193, 0.85), rgba(214, 181, 113, 0.88));*/
    /*background: linear-gradient(20deg, rgba(216, 214, 156, 0.88), rgba(225, 194, 194, 0.85), rgba(171, 172, 255, 0.78));*/
    background: linear-gradient(20deg, rgba(255, 253, 185, 0.88), rgba(228, 197, 197, 0.84), rgba(170, 172, 255, 0.78));

  }

  /*按钮id*/
  #cp{
    /*background: linear-gradient(20deg, rgba(216, 214, 156, 0.88), rgba(225, 194, 194, 0.85), rgba(171, 172, 255, 0.78));*/
    background: linear-gradient(20deg, rgba(212, 214, 108, 0.6), rgba(237, 175, 160, 0.51), rgba(239, 178, 191, 0.58));

    /*color: rgba(105, 124, 230, 0.87);*/
    width: 100%;
    height: 100%;
    font-family: Microsoft YaHei;
    font-size: 20px;
  }

  @import url(https://fonts.googleapis.com/css2?family=Droid+Sans:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap);

  /*时间表的行宽*/
  table thead th {
    color: #263238;
    padding-top: 10px;
    padding-bottom: 10px;
    font-family: Droid Sans;
    border-style: inset;
    border-color: #bdbdbd;
    text-align: center;
    text-transform: none;
    text-decoration: none;
    font-weight: 700;
    font-style: normal;
    font-size: 18px;
  }

  /*按钮*/
  button.el-button.el-button--default.el-button--small.is-round {
    font-family: Droid Sans;
    font-size: 24px;
    padding-top: 12px;
    border-color: #9fa8da;
    border-style: groove;
    text-transform: none;
    text-align: center;
    font-weight: 500;
    font-style: italic;
    line-height: 24px;
    border-bottom-width: 15px;
    padding-bottom: 5px

  }

  .background--custom{
    background: linear-gradient(90deg, #a765f5, #6db0e7, #f5ec92);
    /*background: linear-gradient(90deg, rgba(167, 101, 245, 0.49), rgba(109, 176, 231, 0.47), rgba(245, 236, 146, 0.47));*/
    bcakdrop-filter:blur(50px);
    background-size: 300% 300%;
    animation: gradient 2s alternate infinite;
    position:fixed;

    top: 0;
    left: 0;
    height: 100%;
    width: 100%;

  }
  @keyframes gradient {
    0% {
      background-position:0%;
    }

    100% {
      background-position: 100%;
    }

  }











  button.el-button.el-button--default.el-button--mini {
    text-align: left;
    text-transform: lowercase;
    color: #660000;
    font-weight: 700;
    font-style: italic;
    line-height: 14px;
    background-color: #d9d9d9;
    border-style: outset;
    font-family: Droid Sans;
    font-size: 14px;
    border-color: #ff9900

  }
  div.el-calendar__title {
    font-family: Muli;
    font-size: 24px;
    line-height: 24px;
    font-weight: 500;
    font-style: italic;
    text-decoration: underline;
    text-transform: capitalize;
    text-align: right;
    width: 170px;
    color: #274e13;
    border-style: none;
  }















  div.el-calendar.mycalendar {
    top:30px;
    height: 900px;
  }

  div.el-calendar__body {
    line-height: 18px;
    top:auto;

    /*时间表 的 样式  */
    /*transform:rotate(1deg);*/



  }




  /*/======================/*/
  /*//样式++*/
    /*<style>*/
      /*animation: name duration timing-function delay iteration-count direction;
      animation-name 规定需要绑定到选择器的 keyframe 名称。。
      animation-duration 规定完成动画所花费的时间，以秒或毫秒计。
      animation-timing-function 规定动画的速度曲线。
      animation-delay 规定在动画开始之前的延迟。
      animation-iteration-count 规定动画应该播放的次数。
      animation-direction 规定是否应该轮流反向播放动画。*/

  /*.magnify {  transition-duration: 0.2s; }*/
  /*.magnify:hover, .magnify:focus, .magnify:active {*/
    /*transform: scale(1.1); !*scale改变为原来的几倍*!*/
  /*}*/

</style>

<!--</style>-->
<!--//盒子++-->
<!--<body>-->
<!--<a href="#" class="magnify">放大</a>-->
<!--<a href="#" class="small">放小</a>-->
<!--<a href="#" class="for-bigsmall">for放大放小</a>-->
<!--<a href="#" class="for-magnify">for放大</a>-->
<!--<a href="#" class="for-small">for放小</a>-->
<!--<a href="#" class="wave-magnify">波动放大</a>-->
<!--<a href="#" class="wave-small">波动放小</a><br>-->
<!--<a href="#" class="slant">倾斜边角（看效果）</a>-->
<!--<a href="#" class="LRightYY">左右摇一摇</a>-->
<!--<a href="#" class="UpbelowYY">上下摇一摇</a>-->
<!--<a href="#" class="YY">摇一摇</a>-->
<!--<a href="#" class="SlowAlterBac">缓慢改变背景</a>-->
<!--<a href="#" class="InvertedImage">倒影</a>-->
<!--https://blog.csdn.net/qq_42483491/article/details/89461241?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_utm_term~default-9.no_search_link&spm=1001.2101.3001.4242.6-->
<!--</body>-->
