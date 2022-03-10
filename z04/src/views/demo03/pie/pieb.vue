<template>

  <div id="myChart2"
       :style="{ width: '400px', height: '300px' }">
  </div>

</template>

<script>
import { getPiebFromRequest } from '@/api/getPie'

export default {
  name: 'pieb',
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
      getPiebFromRequest().then(res => { // 这里放了一个包里面  ../
        this.dataPie = res.data
        this.getPie()
      })
    },
    getPie () {
      const myChart = this.$root.echarts.init(
        document.getElementById('myChart2')
      )

      myChart.setOption({
        series: [
          {
            name: '访问来源',
            type: 'pie', // 设置图表类型为饼图
            radius: ['60%', '70%'],
            avoidLabelOverlap: false,
            label: {
              position: 'outer',
              color: '#2fff13',
              fontSize: 12,
              lineHeight: 22,
              lineWidth: 15
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
