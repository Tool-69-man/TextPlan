import Cookies from 'js-cookie'

const key = 'mytk'

/*
 * 获取Token
*/
export function getToken () {
  return Cookies.get(key)
}

/*
 * 设置Token
 *
 *
*/
export function setToken (token) {
  return Cookies.set(key, token)
}
// if (rememberMe) {
/* @description 记住密码状态下的token在Cookie中存储的天数，默认1天 */

// return Cookies.set(TokenKey, token, { expires: 1 })
// } else return Cookies.set(TokenKey, token)

/*
 * 移除Token
*/
export function removeToken () {
  return Cookies.remove(key)
}
