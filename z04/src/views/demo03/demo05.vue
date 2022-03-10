<template>

  <div id="myChart"
       :style="{ width: '300px', height: '300px' }">
  </div>

</template>

<script>

// import  api from '@/axios/api'

export default {
  name: 'demo05',
  data () {
    return {
      text: 'test',
      dataPie: []
    }
  },

  beforeMount () {

  },
  mounted () {
    this.getdata()
    // console.log(this.dataPie);
    // this.$root => app
    this.$nextTick(() => {
      setInterval(this.getdata, 3000)
      alert('a')
    })
  },
  methods: {
    getdata () {
      this.$axios.get('./pie.json').then(res => { // 这里只返回 [{v,n},{}]
        this.dataPie = res.data
        this.getPie()
      })
    },
    getPie () {
      const myChart = this.$root.echarts.init(
        document.getElementById('myChart')
      )

      // this.$axios.get("./pie.json").then(res=>{
      //   // console.log(res.data);
      //   this.dataPie=res.data;

      myChart.setOption({
        series: [
          {
            name: '访问来源',
            type: 'pie', // 设置图表类型为饼图
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
              position: 'outer',
              color: '#0078FF',
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
      // },'json');
      // alert("实时更新");
    }

  }

}

// 请求
// $.get('https://www.runoob.com/static/js/echarts_test_data.json', function (data) {
//   myChart.setOption({
//     series : [
//       {
//         name: '访问来源',
//         type: 'pie',    // 设置图表类型为饼图
//         radius: '55%',  // 饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 55% 长度。
//         data:data.data_pie
//       }
//     ]
//   })
// }, 'json')
//

</script>

<style scoped>

</style>
