import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import * as ElIconModules from '@element-plus/icons'
import 'element-plus/dist/index.css'
import router from './router'
import store from './store'
import axios from 'axios'
// import 'element-plus/lib/theme-chalk/index.css'
// import 'dayjs/locale/zh-cn' //中文
// import locale from 'element-plus/lib/locale/lang/zh-cn'
import * as echarts from 'echarts'
import dayjs from 'dayjs'
// import './plugins/dayjs'
axios.defaults.adapter = require('axios/lib/adapters/http');
// axios.defaults.headers.head.a
const vm = createApp(App)

for(let iconName in ElIconModules){
  vm.component(iconName,ElIconModules[iconName])
}

vm.config.globalProperties.$axios = axios
vm.config.globalProperties.$dayjs = dayjs
// vm.config.globalProperties.$router = router;
vm.use(store)
vm.use(router)
vm.use(ElementPlus, { size: 'small', zIndex: 3000 })
vm.config.globalProperties.echarts = echarts
vm.mount('#app')
