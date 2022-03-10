import { request } from '../util/simplere'

export function toSwaggerFun () {

  return request({
    // url: '/check/swagger',
    url: '/api/swagger',
    // url: 'swagger-ui.html',
    method: 'get'
  })

}


export function toDruidFun () {
  return request({
    // url: '/check/druid',
    url: '/druid/',
    method: 'post'
  })

}
