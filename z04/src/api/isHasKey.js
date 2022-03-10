import { request } from '../util/simplere'

export function delTpCheck (start) { // 删除时 是否需要 检查数据库
  return request({
    url: '/check/beforeDel?start=' + start,
    method: 'post'
    // data: data
  })
}
