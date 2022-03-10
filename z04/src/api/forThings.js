import { request } from '../util/simplere'

export function getThings () {

  return request({
    url: '/thing/getThings',
    method: 'post'

  })
}

export function getThingByName (name) {

  return request({
    url: '/thing/getThingByName?name='+name,
    method: 'post'
  })
}

export function addThing (name,allFeel) {

  return request({
    url: '/thing/addThing?name='+name+'&allFeel='+allFeel,
    method: 'post'
  })
}
export function updateThing (tid,name,allFeel) {

  return request({
    url: '/thing/updateThing?tid='+tid+'&name='+name+'&allFeel='+allFeel,
    method: 'post'
  })
}



export function deleteThingByName (name) {

  return request({
    url: '/thing/deleteThingByName?name='+name,
    method: 'post'
  })
}





















