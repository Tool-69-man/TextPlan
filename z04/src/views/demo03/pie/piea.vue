<template>

  <div id="myChart1"
       :style="{ width: '300px', height: '300px' }">
  </div>

</template>

<script>
import { getPieaFromRequest } from '@/api/getPie'

export default {
  name: 'piea',
  data () {
    return {
      text: 'test',
      dataPie: []
    }
  },
  mounted () {
    this.getdata()
    // this.$nextTick(() => {
    //   setInterval(this.getdata, 30000);
    //   alert("a");
    // });
  },
  methods: {
    getdata () {
      getPieaFromRequest().then(res => { // 这里放了一个包里面  ../
        this.dataPie = res.data
        this.getPie()
      })
    },
    getPie () {
      const myChart = this.$root.echarts.init(
        document.getElementById('myChart1')
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
              color: '#ebff2b',
              fontSize: 12,
              lineHeight: 22,
              lineWidth: 40
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
