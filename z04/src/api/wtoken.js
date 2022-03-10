import { request } from '../util/simplere'

export function myGetToken (data) {
  return request({
    url: '/api/login',
    method: 'post',
    data: data
  })
}

export function getThisDate (data) {
  return request({
    url: '/date?data=' + data, // 路径添加参数
    // url: '/date',  //路径添加参数
    method: 'post'
    // data: {data}      // data
  })
}

// 这个也没用
// export function getThisDate(data){
//
//   console.log(data);
//   const config = {
//     method: 'post',
//     url: '/date'
//   };
//   if(data) {
//     config.data = data
//   }
//   return request(config)
// }

export function getTpStart (start) { // 获取 tp 根据时间
  return request({
    url: '/getTpStart?start=' + start, // 路径添加参数
    method: 'post'
    // data: {data}      // data
  })
}

export function addTp ( start, end, feel, text, tid) { // 获取 tp 根据时间
  return request({
    url: '/addTp?start=' + start  + '&tid=' + tid + '&end=' + end + '&text=' + text + '&feel=' + feel, // 路径添加参数
    method: 'post'
    // data: {
    //   start,

    //   tid,
    //   end,
    //   text,
    //   feel
    // }      // data
  })
}

// updateTp
export function updateTp (did, start, end, feel, text, tid) { // 获取 tp 根据时间
  return request({
    url: '/updateTp?start=' + start + '&did=' + did + '&tid=' + tid + '&end=' + end + '&text=' + text + '&feel=' + feel, // 路径添加参数
    method: 'post'
    // data: {
    //   start,
    //   did,
    //   tid,
    //   end,
    //   text,
    //   feel
    // }      // data
  })
}

export function delTpStart (start) { // 删除 tp 根据时间
  return request({
    url: '/delTpStart?start=' + start, // 路径添加参数
    method: 'post'
    // data: {data}      // data
  })
}
