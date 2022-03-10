import axios from 'axios'
import { getToken } from './token'
import { ElNotification } from 'element-plus'
// import Cookies from "js-cookie";
// import service from "./request";
// import store from "../store";

const instance = axios.create({
  baseURL: '/api',
  timeout: 50000 ,// 请求超时时间


  // headers: {
//     "content-type": "multipart/form-data",
//     // "access-control-allow-headers": Authorization, Content-Type, Depth, User-Agent, X-File-Size, X-Requested-With, X-Requested-By, If-Modified-Since, X-File-Name, X-File-Type, Cache-Control, Origin
//
// "access-control-allow-methods": "GET, POST, OPTIONS, PUT, DELETE",
//
// "access-control-allow-origin": '*'

// access-control-expose-headers: Authorization
//   }

})

// 请求预处理
instance.interceptors.request.use(
  (config) => {
    // const token = getToken() //getToken是在另一个JS文件中封装好的方法
    // token && (config.headers['authorization'] = token);
    config.data = JSON.stringify(config.data) // 数据转化,也可以使用qs转换

    if (getToken()) {
      config.headers.Authorization = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    }

    config.headers['Content-Type'] = 'application/json'
    // config.headers['Content-Type'] = 'content-type'
    // 1.application/x-www-form-urlencoded
    //
    // 最常见的post的数据类型，也是表单提交的数据类型，jquery的ajax默认也是这个
    //
    // 2.multipart/form-data
    //
    // 文件上传时要使用的数据类型
    //
    // 3.application/json
    //
    // json格式的数据类型，也是axios的默认类型
    //
    // 4.text/xml


    config.headers['Access-Control-Expose-Headers'] = 'Content-Disposition'
    return config
  },
  (error) => {
    return Promise.reject(error)
  }

)

instance.interceptors.response.use(response => {
  // 接收到响应数据并成功后的一些共有的处理，关闭loading等

  return response
}, error => {
  console.log('error', error)
  /** *** 接收到异常响应的处理开始 *****/
  if (error && error.response) {
    // 1.公共错误处理
    // 2.根据响应码具体处理
    switch (error.response.status) {
      case 400:
        error.message = '错误请求'
        break
      case 401:
        error.message = '未授权，请重新登录'
        break
      case 403:
        error.message = '拒绝访问'
        break
      case 404:
        error.message = '请求错误,未找到该资源'
        // window.location.href = "/"
        break
      case 405:
        error.message = '请求方法未允许'
        break
      case 408:
        error.message = '请求超时'
        break
      case 500:
        error.message = '服务器端出错'
        break
      case 501:
        error.message = '网络未实现'
        break
      case 502:
        error.message = '网络错误'
        break
      case 503:
        error.message = '服务不可用'
        break
      case 504:
        error.message = '网络超时'
        break
      case 505:
        error.message = 'http版本不支持该请求'
        break
      default:
        error.message = `连接错误${error.response.status}`
    }
  } else {
    // 超时处理
    if (JSON.stringify(error).includes('timeout')) {
      ElNotification({
        title: 'Error',
        message: '服务器响应超时，请刷新当前页',
        duration: 0
      })
      // Message.error('服务器响应超时，请刷新当前页')
    }
    // Message.error('连接服务器失败')
    ElNotification({
      title: 'Error',
      message: '连接服务器失败',
      duration: 0
    })
  }
  // Message.error(error.message)
  ElNotification({
    title: 'Error',
    message: error.message,
    duration: 0
  })

  /** *** 处理结束 *****/
  // 如果不需要错误处理，以上的处理过程都可省略
  return Promise.resolve(error.response)
})
// instance.interceptors.response.use(
//   /**
//    * If you want to get http information such as headers or status
//    * Please return  response => response
//    */
//
//   /**
//    * Determine the request status by custom code
//    * Here is just an example
//    * You can also judge the status by HTTP Status Code
//    */
//   response => {
//     return response.data;
//   }
// )

// response 拦截器
// service.interceptors.response.use(
//   response => {
//     return response.data
//   },
//   error => {
//     // 兼容blob下载出错json提示
//     if (error.response.data instanceof Blob && error.response.data.type.toLowerCase().indexOf('json') !== -1) {
//       const reader = new FileReader()
//       reader.readAsText(error.response.data, 'utf-8')
//       reader.onload = function(e) {
//         const errorMsg = JSON.parse(reader.result).message
//         ElNotification.errors({
//           title: errorMsg,
//           duration: 5000
//         })
//
//         ElNotification.error({
//           title: errorMsg,
//           duration: 5000
//         })
//       }
//     } else {
//       let code = 0
//       try {
//         code = error.response.data.status
//       } catch (e) {
//         if (error.toString().indexOf('Error: timeout') !== -1) {
//
//           ElNotification.errors({
//             title: '网络请求超时',
//             duration: 5000
//           })
//           return Promise.reject(error)
//         }
//       }
//       console.log(code)
//       if (code) {
//         if (code === 401) {
//           store.dispatch('LogOut').then(() => {
//             // 用户登录界面提示
//             Cookies.set('point', 401)
//             location.reload()
//           })
//         } else if (code === 403) {
//           router.push({ path: '/401' })
//         } else {
//           const errorMsg = error.response.data.message
//           if (errorMsg !== undefined) {
//
//             ElNotification.errors({
//               title: errorMsg,
//               duration: 5000
//             })
//
//           }
//         }
//       } else {
//
//
//         ElNotification.errors({
//           title: '接口请求失败',
//           duration: 5000
//         })
//       }
//     }
//     return Promise.reject(error)
//   }
// )

export const request = instance
