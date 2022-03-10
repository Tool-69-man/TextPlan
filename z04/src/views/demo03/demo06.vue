<template >


  <!--<div id="backfilter" :style="timeline">-->

  <!--</div>-->

<!--<div id="backfilter"  :style="backgroundUrl"> </div>-->

  <img :src="scrollImage" id="scrollImage"  /><!--动态图片不行  路径，打包 后  public asset 包下都不行-->
  <!--<img src="../../../public/001/day_night(221).jpg" id="backfilter"  />-->
  <!--<img src="../../assets/001/404.jpg" id="backfilter"  />-->
  <!--<img src="../../assets/xiong.gif" id="backfilter"  />-->

  <!--<div :style="timeline">-->
  <div style="opacity: 1; background: rgba(222, 220, 226, 0.5)">
  <div >
    <el-row>


      <el-col :span="24">

        <el-steps :space="20" :active="1" align-center simple>

          <el-step title="Select Time For Plan" icon="Edit"></el-step>
          <div id="timePicker">
          <el-date-picker
            v-model="ThisDate"
            type="date"
            placeholder="Pick a day"
            :disabled-date="disabledDate"
            :shortcuts="shortcuts"
          >
          </el-date-picker>
          </div>


          <div id="divAdd" style="width: 100px; "></div>

          <el-button type="primary" @click="getListDay">   <el-step title="button" icon="refresh-left"></el-step></el-button>



        </el-steps>
      </el-col>
      <!--<el-col :span="8" >-->

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

    </el-row>

  </div>



    <!--<el-timeline :style="timeline" >-->
    <el-timeline>
    <!--<el-timeline  >-->
      <el-scrollbar    style="backface-visibility: hidden" ref="scrollbar"  @scroll="scroll" max-height="580px"	>




        <el-timeline-item
        v-for="(activity, index) in  dateThing"
        :key="index"
        :icon="activity.iocn"
        :type="activity.type"
        :color="activity.color"
        size="large"
        center="true"
        :timestamp="this.$dayjs(activity.start).format('YYYY-MM-DD HH:mm:ss')"
        @dblclick="iocnCheck(activity,index)"
      >




        <el-drawer
          v-model="drawer[index]"
          direction="ltr"
          title="I am the title"
          :with-header="false"
          :before-close="handleClose"
          size=50%

        >


          <!--<span>Hi there! {{index}}</span>-->

          <div class="demo-drawer__content">
            <el-form
              ref="form"
              :model="form"
              :rules="rules"
              label-width="120px"
              class="demo-ruleForm"
            >

              <el-form-item label="事件标签" prop="thing"  :label-width="labelWidth">
                <el-select
                  loading-text
                  v-model="form.thingId[index]"
                  placeholder="Please select activity area"
                >
                  <el-option v-for="(item,index) in things"
                             :key="item.name"
                             :label="item.name"
                             :value="item.tid"></el-option>
                </el-select>

              </el-form-item>


              <p>可选时间区间： {{pre}} ~ {{after}} (开始和结束时间不能相同) </p>
              <el-form-item prop="startToEnd" label="时间段" :label-width="labelWidth"   required>
              <el-time-picker
                v-model="form.startToEnd"
                is-range="true"
                range-separator="To"
                start-placeholder="Start time"
                end-placeholder="End time"
                :disabled-hours="disabledHours"
                :disabled-minutes="disabledMinutes"
                :disabled-seconds="disabledSeconds"
              >
              </el-time-picker>

              </el-form-item>


              <el-form-item prop="text" label="事件：" :label-width="labelWidth"  >
                <el-input
                  v-model="form.text[index]"
                  :autosize="{ minRows: 1, maxRows: 4 }"
                  type="textarea"
                  placeholder="Please input"
                >
                </el-input>
              </el-form-item>

              <el-form-item  class="editorInDrawer" prop="feel" label="感想：" :label-width="labelWidth"   >
                <!--<el-input-->
                  <!--v-model="form.feel[index]"-->
                  <!--:autosize="{ minRows: 2, maxRows: 6 }"-->
                  <!--type="textarea"-->
                  <!--placeholder="Please input"-->
                <!--&gt;-->

                <!--</el-input>-->
              <el-scrollbar>
                <Editor   v-on:inputWangEditor="WangEditorForValue"  :value="form.feel[index]"></Editor>
              </el-scrollbar>

              </el-form-item>




            </el-form>

            <div class="demo-drawer__footer">
              <el-button @click="cancelForm(index)">Cancel</el-button>
              <el-button
                type="primary"
                :loading="loading"
                @click="addAndup(index,form)"

              >{{ loading ? 'Submitting ...' : 'Submit' }}</el-button
              >
            </div>

          </div>


        </el-drawer>




        <div >

          <el-popover   id="el-pop" offset="100"   hide-after="400" trigger="hover" placement="left" :width="300" >
            <p>双击标记完成</p>

            <div style="text-align: right; margin: 0" id="menu"  >
              <el-button   circle :icon="infoIcon"  size="larger" type="primary"  @click="openDrawer(index)"
              ></el-button
              >
              <el-button icon="close"  circle  type="primary"  size="larger" @click="deleteTimePoint(index)"
              ></el-button>

              <el-tooltip
                class="item"
                effect="dark"
                content="日历"
                placement="bottom"
              >
                <el-button :icon="Calendar"    circle  type="primary"  size="larger"   @click="goCalendar"
                ></el-button>
              </el-tooltip>




              <el-button :icon="Edit"  circle  type="primary"  size="larger" @click="openDrawer(index)"
              ></el-button>






                <el-popover trigger="hover"  placement="top" :width="200">

                  <div style="text-align: right; margin: 0">
                    <el-button size="mini" type="text" @click="addNext(index,10)"
                    >10m</el-button
                    >

                    <el-button type="primary" size="mini" @click="addNext(index,15)"
                    >15m</el-button
                    >

                      <el-button size="mini" type="text" @click="addNext(index,20)"
                      >20m</el-button
                      >

                      <el-button type="primary" size="mini" @click="addNext(index,1)"
                      >1h</el-button
                      >


                  </div>

                  <template #reference>




                    <el-button :icon="DocumentAdd"  circle  type="primary"  size="larger" @click="addNext(index,-1)"
                    ></el-button>


                  </template>

                </el-popover>








            </div>

            <template #reference>



              <el-card shadow="hover"  :style="timeline" id="el_card" >
                <el-container >
                  <el-header>
                    <el-row >


                      <el-col :span="20" id="dateThingForText">

                        <h3 id="h3Text">
                          <el-scrollbar>
                          {{dateThing[index].text}}
                        </el-scrollbar>
                        </h3>


                      </el-col>


                      <el-col :span="4">
                        <el-button color="#cfd8dc" v-show="success[index]"  icon="Check" circle></el-button>
                      </el-col>
                    </el-row>
                  </el-header>


                    <el-main >
                          <!--<el-divider></el-divider>-->
                      <!--<p> Start To End : </p>-->

                      <p> Start   To   End  :  {{this.$dayjs(activity.start).format('YYYY-MM-DD HH:mm:ss')}}  ~  {{this.$dayjs(activity.end).format('HH:mm:ss')}}</p>
                      <!--<p>{{this.$dayjs(activity.start).format('YYYY-MM-DD HH:mm:ss')}}  ~  {{this.$dayjs(activity.end).format('HH:mm:ss')}}</p>-->
                      <!--<el-divider></el-divider>-->

                    </el-main>


                  <el-footer>
                      <el-scrollbar max-height="200px"  >
                        <!--<div class="flex-content">-->
                          <p id="feelTitle">I think: ( •̀ ω •́ )✧ </p>
                          <!--<p id="feelshow"> {{dateThing[index].feel}} </p>-->
                          <div id="feelshow" v-html="dateThing[index].feel"></div>
                        <!--</div >-->
                      </el-scrollbar>
                    </el-footer>






                </el-container>



              </el-card>







            </template>
          </el-popover>
        </div>

      </el-timeline-item>
      </el-scrollbar   >


    </el-timeline>




  </div>






</template>

<script >

import { DocumentAdd, Edit, Calendar, InfoFilled, MoreFilled, MagicStick, Check,CloseBold } from '@element-plus/icons-vue'
import {ElScrollbar,ElNotification, ElConfigProvider, ElMessageBox, ElMessage} from 'element-plus'
import { getThisDate, getTpStart, addTp, updateTp, delTpStart } from '@/api/wtoken'
import {getThings,getThingByName,addThing,updateThing,deleteThingByName} from '@/api/forThings'
import Editor from "../../components/WangEditor.vue";
const makeRange = (start, end) => {
  const result = []
  for (let i = start; i < end; i++) {//输入 0-2  0~1 数字返回
    result.push(i)
  }
  return result
}


export default {
  name: 'demo06',
  components: {Editor},
  // components: {Pie},
  data () {
    return {

      // scrollHeight:ref<InstanceType<typeof ElScrollbar>>(),
      // scrollHeight:reactive(ElScrollbar),
      // scrollHeight:ref(ElScrollbar),

      ThisDate:new Date(),

      shortcuts: [
        {
          text: 'Today',
          value: new Date(),
        },
        {
          text: 'Yesterday',
          value: () => {
            const date = new Date()
            date.setTime(date.getTime() - 3600 * 1000 * 24)
            return date
          },
        },
        {
          text: 'A week ago',
          value: () => {
            const date = new Date()
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7)
            return date
          },
        },
      ],

      timeline:{
        // backgroundColor:"linear-gradient(20deg, rgba(226, 182, 168, 0.98), rgba(199, 219, 243, 0.84), rgb(183, 215, 226));",
        // backgroundColor:"",
        backgroud:"",
        opacity:"0.7"

      },




      scrollRef:null,
      scrollAdd:0,
      scrollTopPre:0,
      ListColorIndex:0,

      DocumentAdd: DocumentAdd,
      Edit: Edit,
      Calendar: Calendar,
      moreIcon: MoreFilled,
      infoIcon: InfoFilled,
      successIcon: Check,
      successCheck: true,


      actType: 'primary',
      color1: '#6151ff', // 时间点 颜色
      color2: '#ff6b68',
      visible: false,
      // dateThing: JSON.parse(this.$route.params.now),
      activities: [{}, {}, {}, {}, {}, {}],
      // thisDateValue:new Date(),
      choiceDate: this.$dayjs(),

      Year:this.$dayjs(this.choiceDate).get('year'),
      Month:this.$dayjs(this.choiceDate).get('month'),
      Day:this.$dayjs(this.choiceDate).get('day'),

      pre:this.$dayjs(),
      after:this.$dayjs(),

      success: [],

      day: [], // 此处用来接收
      dateThing: [],
      drawer:[],  //每个对象的弹窗
      message: [], // 内容输出

      things:[],  //获取 事件标签

      // scrollImage: "../../assets/001/day_night(1).jpg",
      scrollImage: require("../../assets/day_night.gif"),
      imageIndex:0,


      backgroundUrl:{
        background:"url('../../assets/001/day_night(1).jpg')"
      },



      drawerIndex:1,
      loading:false,
      timer: null,

      labelWidth:"80px",

      // value1: [new Date(this.Year, this.Month, this.Day, 8, 40), new Date(2021, 12, 25, 9, 40)],
      value1: [new Date(2000,12,12, 0,0,0 ), new Date(2021, 12, 25, 23, 59,59)],

      // this.$dayjs(this.choiceDate+"01:00:00").format('YYYY-MM-DD HH:mm:ss')
      // , this.$dayjs(this.choiceDate+"23:59:59").format('YYYY-MM-DD HH:mm:ss')

      form: {

        thingId:[],
        startToEnd:[new Date(2000,12,12, 0,0,0 ), new Date(2021, 12, 25, 23, 59,59)],
        // [
        //   new Date(this.Year, this.Month, this.Day, 0, 0 , 20),
        //   new Date(this.Year, this.Month, this.Day, 23,59, 59)
        // ],
        //

        text: [],
        feel: [],
      },
      rules:{

        thing:[
          {
            required: true,
            message: 'Please select Activity zone',
            trigger: 'blur',
          },
          {
            min: 1,
            max: 10,
            message: 'Length should be 3 to 5',
            trigger: 'blur',
          },

        ],


        startToEnd:[
          {
            type: 'date',
            required: true,
            message: 'Please pick time',
            trigger: 'blur',
          },

        ],
        text:[
          {
            required: true,
            message: 'Please input text',

          },
        ],
        feel:[
          {
            required: true,
            message: 'Please input feel',

          },
        ],
      }//rules

    }
  },
  computed:{
    //这里的方法

      //赋值
    // drawerSetText(){

     // return   this.dateThing[this.drawerIndex].thing.name;
    //  return   this.things[this.form.thingId[this.drawerIndex]].name;
    // }

  },
  //这里监测 或计算属性  让
  // start < end  && 下一个tp的开始时间 > 我设定的结束时间 && 上一个

  //如果事件超过45 就 将 init 中数组翻倍

  //如果 things 数组加了


  beforeMount () {

  },
  mounted(){
    this.routeParamsCheck()
    this.init()
    this.getThingsToList();

  }
  ,
  methods: {


    WangEditorForValue(value){
     this.form.feel[this.drawerIndex]=value;
    },



    //滚条展示背景
    scroll(s){

    // this.scrollRef=s;//s 只有 高度和宽度2个属性及其值




    // this.scrollAdd +=s.scrollTop-this.scrollTopPre;
    this.scrollAdd =s.scrollTop;
    this.scrollChanageColor(s);


    // this.scrollChanageImage(s);



      // this.backgroundUrl.background="url("+this.scrollImage+")";

    },
    scrollChanageImage(s){



      let prePath="../../asset/001/day_night(";
      let afterPath=").jpg";
      // this.imageIndex++;
      this.imageIndex = this.imageIndex>1790? 10:this.imageIndex;
      // this.scrollImage=require(prePath+this.imageIndex+ afterPath);

      },
    scrollChanageColor(s){
      // console.log(s.scrollTop);

      let listColor=[

        '#c7ccff',
        '#ff938b',
        '#a5faff',
        '#ffc4fc',
        '#ffeec9',
        '#ade4ff',



      //浅
        '#fce4ec',
        '#f3e5f5'
        ,'#ede7f6',
        '#e8eaf6',
        '#e3f2fd',
        '#e1f5fe'
        ,'#e1f5fe'
        ,'#e0f7fa'
        ,'#e0f2f1'
        ,'#e8f5e9'
        ,'#f1f8e9'
        ,'#f9fbe7'
        ,'#fffde7'
        ,'#fff8e1'
        ,'#fff3e0',
        '#fbe9e7'
        ,'#efebe9'
        ,'#f5f5f5'
        ,'#cfd8dc'
        ,'#c5cae9'
        ,'#d1c4e9'



        ,'#c7ccff',
        '#ff938b',
        '#a5faff',
        '#ffc4fc',
        '#ffeec9',
        '#ade4ff',


      ];

      let listColorThree=[
        'linear-gradient(20deg, rgba(226, 182, 168, 0.98), rgba(199, 219, 243, 0.84), rgb(183, 215, 226))',
        //
        'linear-gradient(to right, #c7ccff 20%,#e0f2f1  70%)',
        //
        'linear-gradient(to right, #ade4ff 20%,#c5cae9  70%)',
        'linear-gradient(to right, #ffcc99 20%,#e3f2fd  70%)',
        'linear-gradient(to right, #ffc4fc 20%,#efebe9  70%)',
        'linear-gradient(to right, #ffeec9 20%,#fffde7  70%)',
        'linear-gradient(to right, #c7ccff 20%,#a5faff  70%)',
        'linear-gradient(to top, #c7ccff 10%,#e3f2fd  60%)',
        'linear-gradient(to top, #ade4ff 10%,#e1f5fe  60%)',
        'linear-gradient(to top, #ffc4fc 10%,#fff8e1  60%)',
        'linear-gradient(to top, #ff938b 10%,#e3f2fd  60%)',
        'linear-gradient(to top, #ff938b 10%,#f3e5f5  60%)',

      ' linear-gradient(20deg, rgba(226, 217, 105, 0.98), rgba(207, 219, 243, 0.84), rgb(72, 171, 226))',
      ' linear-gradient(20deg, rgba(167, 226, 140, 0.98), rgba(237, 215, 243, 0.84), rgb(94, 71, 228))',
      ' linear-gradient(20deg, rgba(145, 226, 220, 0.98), rgba(243, 208, 222, 0.84), rgb(173, 226, 214))',
      ' linear-gradient(20deg, rgba(168, 174, 226, 0.98), rgba(243, 215, 223, 0.84), rgb(226, 130, 109))',
      ' linear-gradient(20deg, rgba(129, 226, 217, 0.98), rgba(243, 236, 220, 0.84), rgb(226, 158, 114))',


      ]



      // let listColor=['#AC4541','#a44142','#7044be'
      //   ,'#6ab772','#5262cf','#e3f2fd','#e1f5fe'
      //   ,'#bb448b','#e0f7fa','#e0f2f1','#e8f5e9','#f1f8e9','#f9fbe7'
      //   ,'#fffde7','#fff8e1','#fff3e0','#fbe9e7','#efebe9','#f5f5f5','#cfd8dc','#c5cae9','#d1c4e9'];



      let bettween =  s.scrollTop -this.scrollTopPre
      // if (s.scrollTop/150<10||s.scrollTop/150>10){//每次移动150 px  +-20 就
      if (Math.abs(bettween)>300){//每次移动超过 150 px
        this.ListColorIndex++;

        // if (listColor.length< this.ListColorIndex){
        //   this.ListColorIndex=0;
        // }
        if (listColorThree.length === this.ListColorIndex){
          this.ListColorIndex=0;
        }

        this.scrollTopPre=s.scrollTop;  //将这一次的位置赋值
        // this.timeline.backgroundColor=  listColor[ this.ListColorIndex];

       // ????
        this.timeline.background=  listColorThree[ this.ListColorIndex];
        // this.timeline.backgroundColor= "#65f8ee"

        // "rgb(" + Math.round(Math.random() * 255) + "," + Math.round(Math.random() * 255) + ',' + Math.round(Math.random() * 10) + ')';
      }

      // if (s.scrollTop  <100) {
      //   this.timeline.backgroundColor= "#f1f8e9";
      // }else if (100<s.scrollTop<200) {
      //   this.timeline.backgroundColor = "#f8e9c2";
      // }
    },



    getRandomColor() {
      return "rgb(" + Math.round(Math.random() * 255) + "," + Math.round(Math.random() * 255) + ',' + Math.round(Math.random() * 10) + ')';
      // return '#' +
      //   (function(color) {
      //     return(color += '0123401234abcabc' [Math.floor(Math.random() * 16)]) &&
      //     (color.length == 6) ? color : arguments.callee(color);
      //   })('');

      }

    ,
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },



    //时间 限制  上下 事件
    disabledHours() {



      let preStart=this.dateThing[this.drawerIndex-1].end+"";//上一个的结束时间给下一个
       let preHour = this.$dayjs(preStart).get('hour');
      // console.log(preHour);





      if(this.dateThing[this.drawerIndex + 1]!==undefined){
        //如果没有下个值 就将 输入 24
        let aftEnd=this.dateThing[this.drawerIndex+1].start+"";//下一个的开始时间是我的结束时间
        let aftHour = this.$dayjs(aftEnd).get('hour');

        //  （   ）  开区间
        return makeRange(0, preHour).concat(makeRange(aftHour+1,24));

      }

        return makeRange(0, preHour)



    },
    disabledMinutes(hour) {

      //同时    pre===hour===after

      // let  list =[];

      let preStart=this.dateThing[this.drawerIndex-1].end+"";//上一个的结束时间给下一个
      let preMinute = this.$dayjs(preStart).get('minute');

      //与上个end 同时 限制  0~preM

      //与下一个同时  限制后半段
      //有下个事件 同时  限制
      //不同时  不限制
      //没下个事件 不限致

      if(this.dateThing[this.drawerIndex + 1]!==undefined){
        //如果没有下个值 就将 输入 24
        let aftEnd=this.dateThing[this.drawerIndex+1].start+"";//下一个的开始时间是我的结束时间
        let aftMinute = this.$dayjs(aftEnd).get('minute');

        //  （   ）  开区间
        if (hour===this.$dayjs(aftEnd).get('hour')) {


          //如果前后时间一样   那么前后分钟 要一起限制     相同时间
          if (hour===this.$dayjs(preStart).get('hour')) {  //如果 前面和后面的hour
                                                          // 都相同 必须在这里判断
            // list.concat(makeRange(0, preMinute)) ;
            return makeRange(0, preMinute).concat(makeRange(aftMinute+1,60));
          }else {
            return makeRange(aftMinute+1,60);//注意这里分钟60
          }

        }


      }//有下个事件


      //
      // dayjs().get('minute');
      // dayjs().get('second');

      if (hour===this.$dayjs(preStart).get('hour')) {  //这里必须 放在 后面  让 后面的时间判断
        // list.concat(makeRange(0, preMinute)) ;
        return makeRange(0, preMinute);
      }





      return []//不同时 就 不限制
    },
    disabledSeconds(hour, minute) {

      //同分同时



      let preStart=this.dateThing[this.drawerIndex-1].end+"";//上一个的结束时间给下一个

      console.log(""+preStart);
      let preMinute = this.$dayjs(preStart).get('minute');
      let preHour =this.$dayjs(preStart).get('hour');
      let preSecond =this.$dayjs(preStart).get('second');

      let aftEnd;
      let aftHour=23
      let aftMinute=59
      let aftSecond=59

      if(this.dateThing[this.drawerIndex + 1]!==undefined){
        //如果没有下个值 就将 输入 24
        aftEnd=this.dateThing[this.drawerIndex+1].start+"";//下一个的开始时间是我的结束时间
        aftMinute = this.$dayjs(aftEnd).get('minute');
        aftHour= this.$dayjs(aftEnd).get('Hour');
        aftSecond= this.$dayjs(aftEnd).get('second');

      }//有下个事件


      //  这里我想错了  本来 想静止使用相同时间

      //但选择时间的2个表  选的时间之间  无法比较
      //2个表 用的 规则函数 是同一个



      // //这里start === end 就
      // if (preHour===aftHour&&preMinute===aftMinute&&preSecond===aftSecond){
      //   //这里 时间相同
      //   //将下一秒
      //   list = makeRange(preSecond,aftSecond+1);//preSecon
      // }


      if (hour===preHour && minute===preMinute) {


        if (hour===aftHour && minute===aftMinute) {
          return makeRange(aftSecond+1,60).concat(makeRange(0, preSecond));//前后相同 小时分钟 限制先后秒数
        }

        return makeRange(0, preSecond);
      }

      //  （   ）  开区间
      if (hour===aftHour && minute===aftMinute) {
        return makeRange(aftSecond+1,60);//注意这里60
      }



      //与下一个同时  限制后半段
      //有下个事件 同时  限制
      //不同时  不限制
      //没下个事件 不限致




      //
      // dayjs().get('minute');
      // dayjs().get('second');




      return [] //不同时 就 不限制


    },

    //查询things
    getThingsToList(){
      getThings().then(res=>{
        console.log("数组"+res.data[res.data.length-1][0].name);
        for (let i = 0; i < res.data[res.data.length-1].length; i++) {
       this.things.push(    res.data[res.data.length-1][i] )  //放入事件对象
        }

      })




    },
    //打开 抽屉
    openDrawer(index){

      this.drawerIndex=index; //此时抽屉 的位置


      if (this.drawerIndex===0){

        ElNotification({
          title: '不能操作第一条',
          message: '请添加下一条',
          offset: 0
          // duration: 0,//不会自动关闭
        }) // 如果没数据 就跳到日期表
        return;
      }

      //判断当前  将tid 赋给抽屉  事件标签的 规则 不能用  可能 他默认空有值
      // this.form.thingId[index]=this.dateThing[index].tid;

      if(this.form.thingId[index]===undefined||this.form.thingId[index].length===0){
        this.form.thingId[index] = this.dateThing[index].tid;
      }



      //为了抽屉  中 时间提示
      let after;//下一个开始
      if(this.dateThing[index + 1]!==undefined){

        // 上一个结束时间
        // 此时还未 添加 tp到数组 index 不用 +2
        after=this.$dayjs(this.dateThing[index + 1].start).format('HH:mm:ss')
      }else {
        after=this.$dayjs(this.choiceDate+"23:59:59").format('HH:mm:ss');
      }
      let pre =  this.$dayjs(this.dateThing[index-1].end).format('HH:mm:ss')

      this.after = after;
      this.pre = pre;




      //让时间  更方便 的使用
      this.form.startToEnd[0]= this.$dayjs(this.dateThing[index].start);
      this.form.startToEnd[1]= this.$dayjs(this.dateThing[index].end);

      //给文本 赋值
      // this.form.thingId[index]

      //可能 .thing   会error
      //此时只会赋值 一下  注意  要 判断是否为 数据库的值

      //text 展示
      this.form.text[index]=this.dateThing[index].text;

      //

      // let animals = [{name: "dog"}, {name: "snake"}, {name: "monkey"}, {name: "donkey"}]
      // let element = {name: "monkey"}
      //
      // animals.some(animal => animal.name === element.name)
      //this.things.includes(this.form.text[index])  // things 是一个对象 用some
      // this.things.some(thing=>thing.name === this.form.text[index])
      if (this.things.some(thing=>thing.name === this.form.text[index])||this.form.text[index]===""){
        this.form.text[index]=this.dateThing[index].thing.name;
      }

      if (this.form.feel[index] ==="undefined"){//输入框没输入 就会变成 字符串"undefined"
        this.form.feel[index] = " ";
      }else {
        this.form.feel[index]=this.dateThing[index].feel;
      }


      //适用计算属性





      this.drawer[index]=true;
    }

    ,
   handleClose(done){     //这里  是抽屉关闭 时自动回调 的  参数不能改 所以传索引 用了drawerIndex
      if (this.loading) {
       return;
     }
      ElMessageBox.confirm('Do you want to submit?')
        .then(() => {
          this.loading = true
          this.timer = setTimeout(() => {
            done();
            this.addAndup(this.drawerIndex);
            // 动画关闭需要一定的时间
            setTimeout(() => {
              this.loading = false
            }, 200)
          //  400
          }, 500)
        //  2000
        })
        .catch(() => {
          ElMessage({
            type: 'info',
            message: 'canceled',
          })

          // catch error
        })
    },

    cancelForm(index) {
      this.loading = false
      this.drawer[index] = false
      clearTimeout(this.timer)
    },

    addAndup(index,form){//判断 did < -1  => add  否则 update


    // 不这里接受 的 dayjs 对象
      //可以用 dayjs().isSame(dayjs('2011-01-01')) 判断相同

      //这里 我直接转字符串
      //说不定 format 后 可以 判断
      if (   this.form.startToEnd[0]+"" ===this.form.startToEnd[1]+""){



        ElNotification({
          title: '时间相同,不能提交',
          message: '请重新设置',

          // duration: 0,//不会自动关闭
        }) // 如果没数据 就跳到日期表
        return;


      }


      //this.dateThing[index]

      // addTp, updateTp   getTpStart
      // this.message: [],  提示信息
      this.message[index] = '已保存';
     //this.form.thingId
     //  text
     //  feel

      // tid 的值

     //  drawerIndex:1,
     //  this.form.startToEnd【0，1】
      //start , end  这里是 dayjs对象
      // did, start, end, feel, text, tid  update 的传参顺序


      //这不能用
      // let startLDT = start.format('YYYY-MM-DD HH:mm:ss')
      // let endLDT = end.format('YYYY-MM-DD HH:mm:ss')
      //校验 时间

      //


      // 这里有个严重的  问题     我在后面 +“”
      //我在这里 如果在  抽屉不改时间  直接提交  会获取
      //1640167800000  ==》 Date 对象 传唤成long  变成字符串
      //会变成 1640167800000
      //时间选择器 初始值 必须是js 日期对象
      // this.form.startToEnd[0]= this.$dayjs(this.dateThing[index].start);
      // this.form.startToEnd[1]= this.$dayjs(this.dateThing[index].end);



      //为了 谨慎  我在后面 +“” 想让他变成字符串 结果会变成 1640167800000
      // let  start =   this.form.startToEnd[0]+"";
      let  start =   this.form.startToEnd[0];
      let  end = this.form.startToEnd[1];


      if ( this.$dayjs.isDayjs(start)){
        console.log("asd");
      }


      if (this.$dayjs(start).isAfter(end)){
        ElNotification({
          title: '时间错误',
          message: '',

          // duration: 0,//不会自动关闭
        }) // 如果没数据 就跳到日期表
        return;
      }


      //判断 did   执行是否add or  update

     this.drawer[index] = false;  //提交后关闭


      // let  start = this.form.startToEnd[0].format('YYYY-MM-DD HH:mm:ss')+"" //vue 返回 对象 object
      // let  end = this.form.startToEnd[1].format('YYYY-MM-DD HH:mm:ss')+""// 可以用ref 比较 好 但
      //






      let  startLDT =   this.$dayjs(start).format('YYYY-MM-DD HH:mm:ss')+"";
      let  endLDT = this.$dayjs(end).format('YYYY-MM-DD HH:mm:ss')+"";

      let feel = this.form.feel[index];
      let text  = this.form.text[index];
      let tid  = this.form.thingId[index];

      let did =this.dateThing[index].did;
      console.log("did=>"+did);
      console.log("s "+startLDT);
      console.log(endLDT);


      if (feel ==="undefined"){//输入框没输入 就会变成 字符串"undefined"
        feel = " ";
      }
      console.log(feel);
      console.log(text);

      if (text ===undefined){  //text 之前赋值了
                          // 即使在输入框清空text 不会是undefined
        text = " ";
      }

      //这里添加text 属于标签组就添加改变



      if (this.things.some(thing=>thing.name ===  text)|| text.trim()===""){
        // text=this.dateThing[index].thing.name;
        // text = "aaa"

        //选择的  标签id   this.form.thingId[index]
        //将text  赋值    things[标签id].name
        //这里得到的标签id 从1开始
        text = this.things[this.form.thingId[index]-1].name;

      }

      console.log(tid+" end ");

      if (this.dateThing[index].did===-1){
        console.log("add后台");
        //这个事件 还没 加入 数据库
        addTp(startLDT, endLDT, feel, text, tid).then(res=>{
          if (res.data !== "TRUE"){
            ElMessage({
              type: 'error',
              message: '后台传入数据失败',
            })
            return;
          }



          //因为数据 提交后 没刷新 就需要  更新数据
          //法一 ： 调用后台接口 更新 根据当前时间 更新  dateThing[index]
          //法二：  调用后台 更新所有数组 (不行 ， 未保存的也会)
          //法三：就直接赋值  不查后台 did 就 用 1



          // 法一 => 会得到正确 的 did 但我不太需要
          //只要再刷新就 会获取后台的 感觉这样会快点


          //这里重大错误 我update did 必须是真的


          // this.dateThing[index].did = 1;

          getTpStart(startLDT).then(res=>{
            // this.dateThing[index]  = JSON.parse(res.data);
            // console.log(JSON.parse(res.data).tid);
            this.dateThing[index] = JSON.parse(res.data);

            if (this.dateThing[index].feel ==="undefined"){//输入框没输入 就会变成 字符串"undefined"
              this.dateThing[index].feel = " ";
            }

            if (this.dateThing[index].text ==="undefined"||this.dateThing[index].text.trim()===""){//输入框没输入 就会变成 字符串"undefined"

              this.dateThing[index].text = this.dateThing[index].thing.name;
            }
            if (this.things.some(thing=>thing.name ===  this.dateThing[index].text)|| this.dateThing[index].text===""){
              this.dateThing[index].text=this.dateThing[index].thing.name;
            }



            this.form.thingId[index] = this.dateThing[index].tid;
          })

          // this.dateThing[index].thing.tid=tid;
          // this.dateThing[index].thing.name=this.things[tid].name;
          // this.dateThing[index].thing.allFeel="";
          // this.dateThing[index].start=startLDT
          // this.dateThing[index].end=endLDT
          // this.dateThing[index].text=text+""
          // this.dateThing[index].feel = feel+"";





          this.message[index] = '已保存';
        })

      }else{

        //这里did 必须是真的did

        //不用虚假值 用法一
        //updateTp 不传 did 后台 还是要查一次


        updateTp(did,startLDT, endLDT, feel, text, tid).then(res=>{
          if (res.data !== "TRUE"){
            ElMessage({
              type: 'error',
              message: '后台传入数据失败',
            })
            return;
          }

          //这里 就 没 查了

          //还是要查
          getTpStart(startLDT).then(res=>{

            this.dateThing[index] = JSON.parse(res.data);
            if (this.dateThing[index].feel ==="undefined"||this.dateThing[index].feel.length===0){//输入框没输入 就会变成 字符串"undefined"
              this.dateThing[index].feel = " ";
            }
            if (this.dateThing[index].text ==="undefined"||this.dateThing[index].text.trim()===""){//输入框没输入 就会变成 字符串"undefined"
              this.dateThing[index].text = this.dateThing[index].thing.name;
            }

              this.form.thingId[index] = this.dateThing[index].tid;

            if (this.things.some(thing=>thing.name ===  this.dateThing[index].text)|| this.dateThing[index].text===""){
              this.dateThing[index].text=this.dateThing[index].thing.name;
            }

          })




          this.message[index] = '已更改';

        })
      }



    },





    init(){
      for (let i = 0; i <50 ; i++) {
        // this.message.push('');   //展示顺序
        this.message.push(i);   //展示顺序
        this.drawer.push(false);  //每个 对象 独有 一个抽屉
      }

      //展示抽屉 都设为 false


    },

    getListDay () {



      // console.log(this.ThisDate);
      // console.log(this.$dayjs(this.ThisDate).format("YYYY-MM-DD"));
      let date = this.$dayjs(this.ThisDate).format("YYYY-MM-DD");
      // console.log(date);
      this.choiceDate = date;
      this.dateThing = [];


      this.dateThing.splice(0, 0, {
        text: '这一天开始了',
        feel: '知行合一,人生意义',
        did: -1,
        tid: 1,
        start: this.choiceDate+ 'T00:00:00',
        end: this.choiceDate + 'T00:00:20',  //20分钟作为 计划 设计

        thing:{
          tid:1,
          name:"临时",
          allFeel:"",
        }
      })




      getThisDate(date) // 传入日期这里 运行 调用 封装 函数 都是 先执行 当前方法 所有执行完 再执行这个函数
        .then(res => {
            if (res.data[res.data.length-1] === undefined) {
              ElNotification({
                title: this.ThisDate,
                message: '后台请求错误',
                offset: 0
              })

              // this.$router.push({
              //   name: 'demo01',
              // })
              //
            }//如果没查到值


          // this.dateThing = res.data[res.data.length-1];
          for (let i = 0; i <res.data[res.data.length-1].length; i++) {
            this.dateThing.push(res.data[res.data.length-1][i]);
          }
          console.log("自设 日期dateTing"+this.dateThing);

          for (let j = 0; j < this.dateThing.length; j++) {
            console.log("text"+this.dateThing[j].text);

            let text = this.dateThing[j].text+"";
            console.log(this.dateThing[j].thing.name);
            if (text.trim()===""||text.trim()===null||text.trim()==="undefined"){
              this.dateThing[j].text =this.dateThing[j].thing.name;
            }




            console.log("text2"+this.dateThing[j].text);
          }

        });




    },

    // 如果路由 没传值过来   就提醒  (转到)
    routeParamsCheck () {


      if (this.$route.params.choiceDate === undefined){//没传任何值的
        this.getListDay();   //这是异步    里面有请求
        // 也可以 不用跳回去 如果当天 没数据 可以自己添加（不然就只能在 别处设置）
        // 在最开始的位置 设置 单个值
        // this.dateThing.splice(0,0,{
        //   text:"这一天开始了",
        //   start:"00:00:00",
        //   start:date+"00:00:00",
        //   end:"00:00:01",
        // })


      }else if (this.$route.params.now === undefined) {//只传了日期 当前日期没有安排
        this.choiceDate = this.$route.params.choiceDate;
        this.dateThing = [];
        this.dateThing.splice(0, 0, {
          text: '这一天开始了',
          feel: '知行合一,人生意义',
          did: -1,
          tid: 1,
          start: this.choiceDate+ 'T00:00:00',
          end: this.choiceDate + 'T00:00:20',  //20分钟作为 计划 设计

          thing:{
            tid:1,
            name:"临时",
            allFeel:"",
          }
        })



      } else {
        // this.day = JSON.parse(this.$route.params.now).data[0]
        this.day = JSON.parse(this.$route.params.now).data[JSON.parse(this.$route.params.now).data.length-1];
        this.dateThing = this.day;
        this.choiceDate = this.$route.params.choiceDate;
        // 开头
        this.dateThing.splice(0, 0, {
          text: '这一天开始了',
          feel: '知行合一,人生意义',
          did: -1,
          tid: 1,
          start: this.choiceDate+ 'T00:00:00',
          end: this.choiceDate + 'T00:00:20',  //20分钟作为 计划 设计

          thing:{
            tid:1,
            name:"临时",
            allFeel:"",
          }
        })// 必须向数组dateThing 添加 单个空对象  vue动态 变化

        // let list = [];
        // for (let i = 0; i <this.day.length ; i++) {
        //   list.push(this.day[i]);
        // }
      }



      //这里检查一下
      // dateThing  里面 text 是否有空值
      for (let i = 0; i < this.dateThing.length; i++) {
        this.dateThing[i].text= (this.dateThing[i].text===""||this.dateThing[i].text===null) ? this.dateThing[i].thing.name :this.dateThing[i].text;
      }


    },
    iocnCheck (activity,index) {
      if (activity.iocn !== this.successIcon) {
        // 这里一开始用  activity.iocn!==Check
        // 但时activity.icon 不识别   我用一个变量接受 就 可以了 神奇
        activity.iocn = Check

        activity.type = this.actType
        this.success[index]=true
        activity.color = this.color1
      } else {
        this.success[index]=false
        activity.iocn = MagicStick
        activity.type = ''
        activity.color = this.color2
      }
    },
    //
    // addTimePoint(){
    //   this.activities.push( {});  //这只能向后添加空事件（快捷的添加tp）
    // },
    addNext (index , timeAdd) { //  具体提交后台  要对空白的 时间点 编辑后提交
      // 总结splice() //1到2个参数按位删除；三个参数以上是按位添加；
      // alert('qwe');
      // this.activities.splice(index+1,0,{timestamp:"2000-12-12"})

      // this.scrollHeight.value.setScrollTop(this.scrollTopPre+150)
      // console.log(this.scrollHeight.value.set);
      // console.log(this.scrollHeight.target);
      // console.log(this.scrollHeight.emits);
      // console.log( this.scrollHeight.emits.scroll);
      //怎么让 scroll 滚动
      // this.scrollHeight += 150;
      // this.scrollHeight.scrollTop+=this.scrollHeight.scrollTop+150;


      // 创建时就 产生合理的时间
      let nowStart // 上一个tp结束 <= 这个开始
      let nowEnd//  这个结束 <= 下个开始
      // 开始 !== 结束

      let after;//下一个开始
      let pre;

      if(this.dateThing[index + 1]!==undefined){

        // 上一个结束时间
        // 此时还未 添加 tp到数组 index 不用 +2
        after=this.$dayjs(this.dateThing[index + 1].start).format('YYYY-MM-DD HH:mm:ss')
      }else {
        after=this.$dayjs(this.choiceDate+"23:59:59").format('YYYY-MM-DD HH:mm:ss');
      }



      // 在页面上 设置日期

      // 产生开始时间
      // this.dateThing[index]   index-1 ===0 那就 00:00:00
      // if (index-1===0){
      //   afterPreEnd = thisDate+""  // 在上面获取设置日期
      // } else{}






      //下一个开始时间


      nowStart=  this.$dayjs(this.dateThing[index].end).format('YYYY-MM-DD HH:mm:ss')

      nowEnd = after;




      //timeAdd  为了添加快捷时间

      // dayjs().add(7, 'day')  h m


      console.log("AA"+this.dateThing[index + 1]);
      switch (timeAdd){
        case -1:
          break;
        case 1:
          nowEnd=this.$dayjs(nowStart).add(1,'h');  //做个判断
          break;
        case 10:
          nowEnd=this.$dayjs(nowStart).add(10,'m');
          break;
        case 15:
          nowEnd=this.$dayjs(nowStart).add(15,'m');
          break;
        case 20:
          nowEnd=this.$dayjs(nowStart).add(20,'m');
          break;
        default:
          break;
      }


      nowEnd=this.$dayjs(nowEnd).format('YYYY-MM-DD HH:mm:ss');
      // alert(nowEnd);
      //或者 超了23:59:59

      // nowEnd= this.$dayjs(this.choiceDate+"23:59:59").format('YYYY-MM-DD HH:mm:ss');


        //判断 简单 添加时间 的按钮
        // 如果时间 大于下一个tp  开始时间
        //改成

      // console.log("这一天的结束"+this.$dayjs(this.choiceDate).endOf('D'));

        //时间变成 明天了 就改为  今天最后 一刻
        if(this.$dayjs(nowEnd).isAfter(this.$dayjs(this.choiceDate+"23:59:59").format('YYYY-MM-DD HH:mm:ss'))){

           nowEnd =  this.$dayjs(this.choiceDate+"23:59:59").format('YYYY-MM-DD HH:mm:ss')
        } else {  //   如果没超过      判断最后的事件
          // 如果nowEnd 是最后

          if(this.$dayjs(nowEnd).isAfter(after)){//如果超过了下个事件点的开始 就变成下个 tp start

            // 上一个结束时间
            // 此时还未 添加 tp到数组 index 不用 +2
            nowEnd=after;


            if(nowEnd!==nowStart) {  //  如果 开始时间 与结束 相同  下面有个提示报错的

              //这里超过了下个结点 时间   提示
              ElNotification({
                title: ' 超过了下个结点,自动转换时间',
                type: 'info',
                position: 'top-left',
                message: '结束时间改为：' + after,
                offset: 0
                // duration: 0,//不会自动关闭
              }) // 如果没数据 就跳到日期表


            }



          }

      }






      if(nowEnd===nowStart){
        ElNotification({
          title: '开始时间等于结束时间',

          message: '中间没时间添加事件，请调整上下事件时间再添加',
          offset: 0
          // duration: 0,//不会自动关闭
        }) // 如果没数据 就跳到日期表
        return null
      }







      // alert(afterPreEnd);
      // alert(beforeNextStart);

      //



      this.drawer.splice(index + 1, 0, false)
      this.message.splice(index + 1, 0, '未保存')
      this.dateThing.splice(index + 1, 0, {
        tid: 1,
        did: -1,
        feel:"",
        text:"",
        end: nowEnd,
        start: nowStart,
        timestamp: nowStart,
        thing:{
          tid:1,
          name:"临时",
          allFeel:""
        }
      })// 必须向数组dateThing 添加 单个空对象  vue动态 变化

      // console.log(this.$refs.scrollbar);
      // //如果添加最后一个数据  这里this.scrollAdd  再 +300
      // if (index===this.dateThing.length-2){//这里有个开头要去
      //   // this.$refs.scrollbar.setScrollTop(this.$refs.scrollbar.sizeHeight);
      //   // this.$refs.scrollbar.setScrollTop(this.scrollAdd+300);
      //   this.$refs.scrollbar.setScrollTop(this.$refs.scrollbar.sizeHeight-100);
      //
      // }


      // if (this.scrollRef!==null){
      if (this.scrollAdd!==0){
          // let value=this.scrollRef.scrollTop+500;
        //旧版js 文档  真的是强   服了  10s的事情 啊
        //自己琢磨了1个小时，这里的ref提高认知
        this.$refs.scrollbar.setScrollTop(this.scrollAdd+300);


        //这里  增加最底下的数组  s.scrollTop 不变了
        //滑动条高度不变

      }else{
        this.$refs.scrollbar.setScrollTop(200);
      }



        // this.$refs.scrollbar.setScrollTop(150);





    },
    // 删除 当前 时间点
    deleteTimePoint (index) {
      // 删除当前数组索引值 后
      // 判断 数据库（内存）中是否有值  有就删除  ==》接口
      // 放弃的想法 ： 给每个对象 设个flag  来判断当前数据 是否提交 （不可取，有些人直接在浏览器篡改，也熊）

      // type: 'success',    type: 'info',   type: 'error',
      // position: 'bottom-right',  position: 'bottom-left',    position: 'top-left',



      //不能删除第一个

      if (index===0){
        ElNotification({
          title: '无效删除',
          type: 'warning',
          position: 'top-left',
          message: '不能删除第一个事件',
          offset: 0
          // duration: 0,//不会自动关闭
        })


        return null
      }


      // console.log(this.$dayjs(this.dateThing[index].start).format('YYYY-MM-DD HH:mm:ss'));
      delTpStart(this.$dayjs(this.dateThing[index].start).format('YYYY-MM-DD HH:mm:ss'))
        .then(res=>{
          console.log(res);
          if (res.data==='TRUE'){
            //即使删除0条 数据   程序运行正常  删除 dateThing数组 当前对象
            this.dateThing.splice(index,1);

            //message数组也删除
            this.message.splice(index,1);
            this.drawer.splice(index,1)
            // this.form.thingId.splice(index, 1);

          }

        })



      // this.$refs.scrollbar.setScrollTop(this.scrollRef.scrollTop-150);

    },

    goCalendar () {
      this.$router.push({ path: '/demo01' })
    }
    // 点击后 这里添加 空白 事件

  }

}
</script>

<style>

  body{
    margin: 0px;
  }


  button.el-button.el-button--default.el-button--small.is-circle {
    color: #b71c1c;
    background-color: #e0f2f1;
    border-left-width: 7px;
    text-decoration: none;
    border-color: #f57c00;
    border-right-width: 10px;
    padding-left: 5px;
    border-style: dashed

  }




  #el_card{
    font-weight: 700;
    font-style: italic;
    padding: 2px;
    height: auto;
    /*z-index: 100000;*/
    border: dotted #c18dc9;
    text-decoration: none;
    text-transform: none;
    /*line-height: 30px;*/
    /*font-size: 24px;*/
    font-family: Droid Serif;
    text-align: left;
    background-color: #eceff1;
  }

  div.el-card__body {
    padding-top: 0px;
    padding-right: 0px;
    padding-left: 0px;
    padding-bottom: 0px
  }


   .el-timeline-item{
    width: 700px;
    height: auto;
    left: 40%;
    top: 20%;
    bottom: 20%;
    right:  50%;
    border: 20px;
  }



  #dateThingForText{

    height: 25px;


  }


  .timeCard{
    height: auto;
    width: auto;
  }



#feelshow{
  /*feel 的标签*/
  height: auto ;
  /*overflow-x: hidden;*/
  word-break: break-all;
  line-height: 2em;
  text-indent: 2em;
  font-style: normal;
}

  .demo-drawer__content{
    left: 20px;
  }


  main.el-main {
    padding-right: 0px;
    padding-bottom: 0px;
    padding-left: 22px;
    padding-top: 10px;
    /*height: auto;*/
    text-transform: none;
    text-decoration: none;
    text-align: left;
    color: #303f9f;
    /*height: 130px;*/
    overflow-x: hidden;
    /*overflow-y: hidden;*/
    border-bottom:  2px dashed var(--el-border-color-base);;
    border-bottom-color: rgb(62, 62, 62);
  }

  @import url(https://fonts.googleapis.com/css2?family=Droid+Serif:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap);

  div.el-popover.el-popper.is-light {
    text-align: left;
    background-color: #cfd8dc;
    padding-left: 0px;
    padding-top: 0px;
    border-style: groove;
    border-color: #78909c;
    font-family: Droid Serif;
    font-weight: 300;
    font-style: italic;

    line-height: 14px;


    text-decoration: underline;

  }

  ul.el-timeline {
    text-align: left;
    /*background-color: #f9fbe7;*/
    margin-left: 0px;


    border-color: #cfd8dc;
    border-style: solid;
    text-transform: capitalize;
  }

  ul.el-timeline {

    border-top-width: 0px;
    border-right-width: 0px;
    border-left-width: 0px;
    border-bottom-width: 0px;

  }


  div.el-card__body {
    font-family: Droid Sans;
    font-size: 18px;
    line-height: 18px;
    text-align: left;
    text-transform: capitalize;
    font-weight: 500;
    font-style: italic;
    /*color: #ffba77;*/
    /*color: rgba(0, 76, 114, 0.9);*/
    /*background: linear-gradient(20deg, rgba(185, 255, 246, 0.66), rgba(118, 134, 197, 0.66), rgba(220, 12, 8, 0.23));*/
    /*background: linear-gradient(20deg, rgba(161, 221, 212, 0.66), rgba(128, 145, 213, 0.37), rgba(255, 14, 9, 0.28));*/
    /*background: linear-gradient(20deg, rgb(122, 184, 255), rgb(255, 255, 255), rgba(255, 77, 0, 0.65));*/
    /*background-color: #ffffff;*/
    background-color: #dddddd;

    box-shadow: 0 10px 15px rgba(0, 0, 0, .4);
    background-size: cover;



    /*background: linear-gradient(20deg, rgba(122, 168, 161, 0.66), rgba(101, 114, 168, 0.37), rgba(255, 14, 9, 0.28));*/
    border-style: outset;
    border-color: #e1bee7;
    opacity:0.9;
    overflow: hidden;

    box-shadow: 0 0 0 1px hsla(0,0%,100%,.3) inset,
    0 .5em 1em rgba(0,0,0,0.6);
    text-shadow: 0 1px 1px hsla(0,0%,100%,.3);


  }


  div.el-card__body::before{
    content: '';
    position: absolute;
    top: 0; right: 0; bottom: 0; left: 0;
    margin: -30px;

    /*-webkit-filter: blur(20px);*/
    /*filter: blur(20px);*/
  }

  div.el-card__body :hover{
    opacity:1;
  }

  body  {
    overflow: hidden;
  }


  header.el-header {
    height: 30px;
  }
  @import url(https://fonts.googleapis.com/css2?family=Crimson+Text:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap);

  /*
  div.el-scrollbar__view {
    border-right-width: 0px;
    margin-right: 0px;
    padding-right: 0px;



  }

  #backfilter {
    border-style: dotted;
    border-color: #bf360c;
  } */

  demo-drawer__content {
    color: #afb42b;
  }

  section.el-drawer__body {
    transform: scale(1,1);
    margin-left: 70px;
    padding-right: 0px;
    margin-bottom: 0px;
    border-style: dashed;
    border-color: #ffeb3b;
    background-color: #eeeeee;
    color: #5c6bc0;
    font-weight: 300;
    font-style: normal;
    font-size: 14px;
    line-height: 24px;
    font-family: Crimson Text;
    text-transform: none;
    text-align: left;
  }


  section header h3 {
    font-size: 24px;
    line-height: 25px;
    color: #162025;
    padding-top: 0px;
    border-top-width: 0px;
    padding-right: 0px;
    border-right-width: 0px;
    text-align: left;
    text-transform: none;
    text-decoration: none;
    overflow-y: hidden;
    border-style: none;
    border-color: #a08076;
    height: auto;
    opacity: 1;

  }
  /*border-top: 2px dashed var(--el-border-color-base);*/
  h3{
    display:inline !important;
  }

  div.el-col.el-col-20 {
    /* overflow: hidden; */
    height: 25px;
    margin-top: 20px;
  }

  div.el-scrollbar__thumb {
    display: none;
  }


#backfilter {
  /*背景*/
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  /*filter: blur(22px);*/
  z-index: -1;
  opacity: 0.5;
  /*background: url('../../assets/001/day_night(1).jpg');*/
  /*background-color: #565ece;*/
  background: linear-gradient(20deg, rgba(226, 182, 168, 0.98), rgba(221, 255, 218, 0.84), rgb(220, 226, 152));
}

#scrollImage{
  position: absolute;
  left: 0;
  top: 0;
  width:100%;
  height:100%;
  filter: blur(3px);
  z-index: -2;
}

  div.el-col.el-col-6 {
    text-align: center;
    padding-top: 0px;
  }

  div main p {
    text-transform: none;
    text-decoration: none;
    line-height: 24px;
    font-size: 18px;
    font-weight: 700;
    font-style: italic;
  }


  div.el-popover.el-popper.is-light {


  }


  div.w-e-toolbar {
    /*position:fixed;*/
    background-color: #fff8e1;
    padding-right: 0px;
  }

  div.w-e-text {

    text-transform: none;
    background-color: #f9fbe7;
  }



  .editorInDrawer{
    /*scroll : */
    overflow:auto;
    height: 300px;
  }

  footer.el-footer {
    /*height: 150px;*/
    height: auto;

  }



  body,main::before{
    /*background-image: url("images/background.jpg")；*/
    background-repeat: no-repeat;
    background-size: cover;
    background-attachment:fixed;

  }



  main::before{
    content: '';
    position: absolute;
    top: 0; right: 0; bottom: 0; left: 0;
    margin: -30px;
    z-index: -1;
    -webkit-filter: blur(20px);
    filter: blur(20px);
  }


  div.el-col.el-col-10 {
    text-align: right;
    padding-bottom: 12px;
    padding-top: 12px;
  }


  div.el-col.el-col-8 {
    text-align: center;

    padding-bottom: 12px;
    padding-top: 12px;
  }
  div.el-col.el-col-6 {
    text-align: left;
    padding-bottom: 12px;
    padding-top: 12px;
  }
  div.el-step.is-simple {
    /* text-align: center; */
    text-transform: uppercase;
    /* font-weight: 100; */
    font-style: italic;
    line-height: 12px;
    font-size: 14px;
    padding-left: 120px;

  }
  #timePicker {
    text-align: right;
    text-transform: none;
    width: 222px;
    background-color: #fafafa;
    border-color: #81d4fa;
    border-style: outset

  }
</style>

<!--:color="activity.color"-->
<!--:size="activity.size"-->
<!--:hollow="activity.isSelected? !activity.hollow:activity.hollow"-->

<!--版本3.0+  触摸显示-->
<!--<template>-->

  <!--<div class="block" >-->
    <!--<el-timeline>-->

      <!--<el-popover v-model:visible="visible" offset=""  trigger="hover" placement="left" :width="200">-->
        <!--<p>Are you sure to delete this?</p>-->
        <!--<div style="text-align: right; margin: 0">-->
          <!--<el-button size="mini" type="text" @click="visible = false"-->
          <!--&gt;cancel</el-button-->
          <!--&gt;-->
          <!--<el-button type="primary" size="mini" @click="visible = false"-->
          <!--&gt;confirm</el-button-->
          <!--&gt;-->
        <!--</div>-->
        <!--<template #reference>-->

            <!--<el-timeline-item-->
              <!--v-for="(activity, index) in activities"-->
              <!--:key="index"-->
              <!--:icon="activity.iocn"-->
              <!--:type="activity.type"-->
              <!--:color="activity.color"-->
              <!--size="large"-->
              <!--:timestamp="activity.timestamp"-->
              <!--@dblclick="iocnCheck(activity)"-->
            <!--&gt;-->

              <!--<div id="card">-->
                    <!--<el-card shadow="hover"  @click="visible=true">-->

                        <!--<h1>{{activity.content}}</h1>-->
                        <!--<h1 >{{message[index]}} </h1>-->
                      <!--</el-card>-->
              <!--</div>-->

            <!--</el-timeline-item>-->

        <!--</template>-->
      <!--</el-popover>-->

    <!--</el-timeline>-->

  <!--</div>-->

  <!--<el-button type="success" @click="add">ADD</el-button>-->
<!--</template>-->

<!--<script>-->
  <!--import { MoreFilled,MagicStick,Check } from '@element-plus/icons-vue'-->

  <!--export default {-->
    <!--name: "demo06",-->
    <!--data(){-->
      <!--return{-->
        <!--successIcon: Check,-->
        <!--successCheck: true,-->
        <!--message:["1","2","3","4","5","6","7","8","9"], //传入我要展示的数据-->

        <!--actType:"primary",-->
        <!--color1:"#0005ff",-->
        <!--color2:"#ff1722",-->
        <!--visible:true,-->

        <!--activities: [{},{},{},{},{},{}]-->
      <!--}-->
    <!--},-->
    <!--methods:{-->
      <!--iocnCheck(activity){-->
        <!--if (activity.iocn!==this.successIcon){-->
          <!--//这里一开始用  activity.iocn!==Check-->
          <!--//但时activity.icon 不识别   我用一个变量接受 就 可以了 神奇-->
          <!--activity.iocn=Check;-->

          <!--activity.type= this.actType;-->

          <!--activity.color=this.color1-->

        <!--}else {-->
          <!--activity.iocn=MagicStick;-->
          <!--activity.type='';-->
          <!--activity.color=this.color2-->
        <!--}-->
      <!--},//点击后 显示 完成-->
      <!--add(){-->
        <!--this.activities.push( {});-->
      <!--}-->

    <!--}-->

  <!--}-->
<!--</script>-->

<!--<style>-->

  <!--.el-timeline-item{-->
    <!--/*text-align-all: center;*/-->
    <!--/*position: center;*/-->
    <!--/*background-color: crimson;*/-->
    <!--width: 400px;-->
    <!--left: 40%;-->
    <!--top: 20%;-->
    <!--bottom: 20%;-->
    <!--right:  50%;-->
    <!--height: auto;-->
  <!--}-->

  <!--/*#card{*/-->
    <!--/*width: 100px;*/-->
  <!--/*}*/-->

  <!--.el-card{-->
    <!--/*position:absolute;*/-->
    <!--/*top:30px;*/-->
    <!--/*right:5px;*/-->
    <!--background: rgb(227, 234, 237);-->
    <!--height: auto;-->
    <!--/*width: 100px;*/-->
  <!--}-->
<!--</style>-->

<!--&lt;!&ndash;版本2.0+ card&ndash;&gt;-->
<!--<template>-->

  <!--<div class="block" >-->
    <!--<el-timeline>-->
      <!--<el-timeline-item-->
        <!--v-for="(activity, index) in activities"-->
        <!--:key="index"-->

        <!--:icon="activity.iocn"-->
        <!--:type="activity.type"-->
        <!--:color="activity.color"-->
        <!--size="large"-->
        <!--:timestamp="activity.timestamp"-->
        <!--@dblclick="iocnCheck(activity)"-->
      <!--&gt;-->
        <!--&lt;!&ndash;:hollow="activity.isSelected? !activity.hollow:activity.hollow" 没搞好&ndash;&gt;-->
        <!--<div id="card">-->
          <!--<el-card shadow="hover"  >-->
            <!--<h1>{{activity.content}}</h1>-->
            <!--<h1 >{{message[index]}} </h1>-->
          <!--</el-card>-->
        <!--</div>-->

      <!--</el-timeline-item>-->
    <!--</el-timeline>-->
  <!--</div>-->

  <!--<el-button type="success" @click="add">ADD</el-button>-->
<!--</template>-->

<!--<script>-->
  <!--import { MoreFilled,MagicStick,Check } from '@element-plus/icons-vue'-->

  <!--export default {-->
    <!--name: "demo06",-->
    <!--data(){-->
      <!--return{-->
        <!--successIcon: Check,-->
        <!--successCheck: true,-->
        <!--message:["1","2","3","4","5","6","7","8","9"],-->
        <!--actType:"primary",-->
        <!--color1:"#0005ff",-->
        <!--color2:"#ff1722",-->

        <!--activities: [{},{},{},{},{},{}]-->
      <!--}-->
    <!--},-->
    <!--methods:{-->
      <!--iocnCheck(activity){-->
        <!--if (activity.iocn!==this.successIcon){-->
          <!--//这里一开始用  activity.iocn!==Check-->
          <!--//但时activity.icon 不识别   我用一个变量接受 就 可以了 神奇-->
          <!--activity.iocn=Check;-->

          <!--activity.type= this.actType;-->

          <!--activity.color=this.color1-->

        <!--}else {-->
          <!--activity.iocn=MagicStick;-->
          <!--activity.type=''-->
          <!--activity.color=this.color2-->
        <!--}-->
      <!--},//点击后 显示 完成-->
      <!--add(){-->
        <!--this.activities.push( {});-->
      <!--}-->

    <!--}-->

  <!--}-->
<!--</script>-->

<!--<style>-->

  <!--.el-timeline-item{-->
    <!--/*text-align-all: center;*/-->
    <!--/*position: center;*/-->
    <!--/*background-color: crimson;*/-->
    <!--width: 400px;-->
    <!--left: 40%;-->
    <!--top: 20%;-->
    <!--bottom: 20%;-->
    <!--right:  50%;-->
    <!--height: auto;-->
  <!--}-->

  <!--/*#card{*/-->
  <!--/*width: 100px;*/-->
  <!--/*}*/-->

  <!--.el-card{-->
    <!--/*position:absolute;*/-->
    <!--/*top:30px;*/-->
    <!--/*right:5px;*/-->
    <!--background: rgb(227, 234, 237);-->
    <!--height: auto;-->
    <!--/*width: 100px;*/-->
  <!--}-->
<!--</style>-->

<!--<template>-->
<!--<div class="hello">-->
<!--<div id="focus_toolTip" class="special_focus_toolTip" v-html="toolTopbody" ></div>-->
<!--<button-->
<!--class="buttonStyle"-->
<!--@mouseover="itemMouseover"-->
<!--@mouseout="itemMouseout"-->
<!--&gt;悬浮框测试</button>-->
<!--</div>-->
<!--</template>-->
<!--<script>-->
<!--//&lt;!&ndash;@mousemove="itemMousemove($event)"&ndash;&gt;-->
<!--import $ from "jquery";-->
<!--export default {-->
<!--name: "demo07",-->
<!--data() {-->
<!--return {-->
<!--toolTopbody: "xxx",-->
<!--message:"msg"-->
<!--};-->
<!--},-->
<!--methods: {-->
<!--itemMouseover: function() {-->
<!--var focusTooltip = $("#focus_toolTip");-->
<!--focusTooltip.css("display", "block");-->

<!--// focusTooltip.css("top", e.clientY - 80 + "px");-->
<!--// focusTooltip.css("left", e.clientX + 100 + "px");-->
<!--focusTooltip.css("top",  80 + "px");-->
<!--focusTooltip.css("left", 100 + "px");-->
<!--// var headerHtml =-->
<!--//   "<div style='font-size:12px;color: #fec443;font-weight: bold;font-family: MicrosoftYaHei;'>" +-->
<!--//   "我的悬浮框参考：" +-->
<!--//   "</div>";-->
<!--// var effectHtml =-->
<!--//   "<div style='font-size:12px;margin-top:5px;'>" +this.message+ "</div>";-->
<!--// this.toolTopbody = headerHtml + effectHtml;-->
<!--this.toolTopbody = "<el-button type='success'>asd</el-button>";-->
<!--},-->

<!--itemMouseout: function() {-->
<!--var focusTooltip = $("#focus_toolTip");-->
<!--focusTooltip.css("display", "none");-->
<!--},-->

<!--// itemMousemove: function(e) {-->
<!--//   var self = this;-->
<!--//   var focusTooltip = $("#focus_toolTip");-->
<!--//   focusTooltip.css("top", e.clientY - 80 + "px");-->
<!--//   focusTooltip.css("left", e.clientX + 100 + "px");-->
<!--//   var headerHtml =-->
<!--//     "<div style='font-size:12px;color: #fec443;font-weight: bold;font-family: MicrosoftYaHei;'>" +-->
<!--//     "我的悬浮框参考：" +-->
<!--//     "</div>";-->
<!--//   var effectHtml =-->
<!--//     "<div style='font-size:12px;margin-top:5px;'>" + "</div>";-->
<!--//   self.toolTopbody = headerHtml + effectHtml;-->
<!--// }-->
<!--}-->
<!--};-->
<!--</script>-->
<!--&lt;!&ndash; Add "scoped" attribute to limit CSS to this component only &ndash;&gt;-->
<!--<style scoped>-->
<!--.buttonStyle {-->
<!--margin-left: 150px;-->
<!--}-->
<!--.special_focus_toolTip {-->
<!--z-index: 7;-->
<!--position: absolute;-->
<!--display: none;-->
<!--width: 100px;-->
<!--height: 130px;-->
<!--border-style: solid;-->
<!--transition: left 0.4s cubic-bezier(0.23, 1, 0.32, 1),-->
<!--top 0.4s cubic-bezier(0.23, 1, 0.32, 1);-->
<!--background-color: rgba(50, 50, 50, 0.701961);-->
<!--border-width: 10px;-->
<!--border-color: #333333;-->
<!--border-radius: 4px;-->
<!--color: #ffffff;-->
<!--font-style: normal;-->
<!--font-variant: normal;-->
<!--font-weight: normal;-->
<!--font-stretch: normal;-->
<!--font-size: 14px;-->
<!--font-family: "Microsoft YaHei";-->
<!--line-height: 21px;-->
<!--padding: 10px 10px;-->
<!--}-->
<!--</style>-->

<!--时间线版本1.0-->
<!--<template>-->
  <!--<div class="block">-->
    <!--<el-timeline>-->
      <!--<el-timeline-item-->
        <!--v-for="(activity, index) in activities"-->
        <!--:key="index"-->
        <!--:icon="activity.iocn"-->
        <!--:type="activity.type"-->
        <!--:color="activity.color"-->
        <!--:size="activity.size"-->
        <!--:hollow="activity.isSelected? !activity.hollow:activity.hollow"-->
        <!--:timestamp="activity.timestamp"-->
        <!--@click="iocnCheck(activity)"-->
        <!--@hover="show(activity)"-->
      <!--&gt;-->
        <!--&lt;!&ndash;:hollow="activity.isSelected? !activity.hollow:activity.hollow" 没搞好&ndash;&gt;-->

        <!--{{ activity.content }}-->
        <!--{{activity.isSelected}}-->
        <!--asd-->
      <!--</el-timeline-item>-->
    <!--</el-timeline>-->
  <!--</div>-->
<!--</template>-->

<!--<script>-->
  <!--import { MoreFilled,MagicStick,Check } from '@element-plus/icons-vue'-->

  <!--export default {-->
        <!--name: "demo06",-->
      <!--data(){-->
        <!--return{-->
          <!--successIcon: Check,-->
          <!--successCheck: true,-->
          <!--activities: [-->
            <!--{-->
              <!--content: 'Custom icon',-->
              <!--timestamp: '2018-04-12 20:46',-->
              <!--size: 'large',-->
              <!--type: 'primary',-->
              <!--icon: MoreFilled,-->
            <!--},-->
            <!--{-->
              <!--content: 'Custom color',-->
              <!--timestamp: '2018-04-03 20:46',-->
              <!--color: '#0bbd87',-->
            <!--},-->
            <!--{-->
              <!--content: 'Custom size',-->
              <!--timestamp: '2018-04-03 20:46',-->
              <!--size: 'large',-->
              <!--color: '#8c70bd',-->
              <!--icon: MagicStick-->
            <!--},-->
            <!--{-->
              <!--content: 'Custom hollow',-->
              <!--timestamp: '2018-04-03 20:46',-->
              <!--type: 'primary',-->
              <!--hollow: true,-->
            <!--},-->
            <!--{-->
              <!--content: 'Default node',-->
              <!--timestamp: '2018-04-03 20:46',-->
            <!--},-->
          <!--],-->
        <!--}-->
      <!--},-->
    <!--methods:{-->
          <!--iocnCheck(activity){-->
              <!--if (activity.iocn!==this.successIcon){-->
                <!--//这里一开始用  activity.iocn!==Check-->
                <!--//但时activity.icon 不识别   我用一个变量接受 就 可以了 神奇-->
                <!--activity.iocn=Check-->
              <!--}else {-->
                <!--activity.iocn=MagicStick;-->
              <!--}-->
          <!--},//点击后 显示 完成-->

          <!--//展示 触碰到-->
          <!--show(activity){-->
            <!--console.log(activity);-->
          <!--}-->

    <!--}-->

    <!--}-->
<!--</script>-->
