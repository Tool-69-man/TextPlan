import { request } from '../util/simplere'

export function getPieFromRequest (nowdate) {
  alert(nowdate)
  console.log(nowdate)
  return request({
    url: '/pie/pie?nowdate=' + nowdate,
    method: 'post'
    // data: data
  })
}

export function getPieaFromRequest () {
  return request({
    url: '/pie/piea',
    method: 'post'
    // data: data
  })
}

export function getPiebFromRequest () {
  return request({
    url: '/pie/pieb',
    method: 'post'
    // data: data
  })
}
