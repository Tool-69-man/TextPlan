<template>

  <div>xxxxx{{nowdate}}</div>
  <div id="myChart"
       :style="{ width: '400px', height: '300px' }">
  </div>

</template>

<script>
// import {getPie} from "../../../api/getPie";
import { getPieFromRequest } from '@/api/getPie'

export default {
  name: 'pie',
  props: ['nowdate'],
  data () {
    return {
      text: 'test',
      dataPie: []

    }
  },
  mounted () {
    this.getdata()
  },
  methods: {

    getdata () {
      // this.$axios.get("../pie.json")
      console.log('*****' + this.nowdate)
      getPieFromRequest(this.nowdate)
        // getPieFromRequest("2020-12-16")
        .then(res => { // 这里放了一个包里面  ../
          console.log(res)
          this.dataPie = res.data[0]// 这里就从后台 传过来

          // [{value: , name: },{}]  value 为相差的毫秒数   name 为事件
          this.getPie()
        })
    },
    getPie () {
      console.log(this.dataPie)

      const myChart = this.$root.echarts.init(
        document.getElementById('myChart')
      )

      myChart.setOption({
        series: [
          {
            name: '访问来源',
            type: 'pie', // 设置图表类型为饼图
            radius: ['30%', '70%'],
            avoidLabelOverlap: false,
            label: {
              position: 'outer',
              color: '#2900ff',
              fontSize: 12,
              lineHeight: 22,
              lineWidth: 30
            },
            emphasis: {
              label: {
                show: true
              }
            },
            labelLine: {
              show: true
            },
            data: this.dataPie
          }
        ]
      })
    }

  }

}

</script>
