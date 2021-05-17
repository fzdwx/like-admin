import axios from 'axios'
import Vue from 'vue'
import commonUtil from '../utils/commonUtil'
import {getToken, setToken, localStorageKey, setUserRole, clear} from '@/utils/authUtil'

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
// 创建axios实例Vue.prototype.$baseURL
const BASE_URL = Vue.prototype.$baseURL
const service = axios.create({
    // axios中请求配置有baseURL选项，表示请求URL公共部分
    baseURL: BASE_URL,
    // 超时
    timeout: 30000
})
service.defaults.withCredentials = true

// 请求拦截器
service.interceptors.request.use(function (config) {
    if (!getToken()) {
        config.headers[localStorageKey.token] = getQueryString(localStorageKey.token)
        // const auth = post('/auth/current_user')
        // console.log(auth)
        // return systemError(error)
    } else {
        config.headers[localStorageKey.token] = getToken()
    }
    // config.headers["Access-Control-Allow-Origin"] = "*";
    return config
}, function (error) {
    return systemError(error)
})


// 响应拦截器
service.interceptors.response.use(function (response) {
    console.log('interceptors.response-------------------' + JSON.stringify(response.headers))
    if (response.headers[localStorageKey.token]) {
        setToken(response.headers[localStorageKey.token])
    }
    // 未设置状态码则默认成功状态
    const result = response.data
    /*  if (result.success === undefined || null) {
          return response.data
      }*/
    const data = result.data

    if (data !== undefined && data[localStorageKey.token] !== undefined && data[localStorageKey.token] !== '' && data[localStorageKey.token] != null && data[localStorageKey.token] !== ""
    ) {
        setToken(data[localStorageKey.token])
        setUserRole(data[localStorageKey.userRole])
    }

    const config = response.config
    if (result.success) {
        // return JSON.stringify(data)
        return data;
    } else {
        return bizError(result, config)
    }
}, function (error) {
    return systemError(error)
})

export function getQueryString(name) {
    const reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)')
    const r = window.location.search.substr(1).match(reg)
    if (r != null) {
        return unescape(r[2])
    }
    return null
}

// 系统错误处理
function systemError(error) {
    let {message} = error
    if (message === 'Network Error') {
        message = '后端接口连接异常'
    } else if (message.includes('timeout')) {
        message = '系统接口请求超时'
    } else if (message.includes('Request failed with status code')) {
        message = '系统接口' + message.substr(message.length - 3) + '异常'
    }
    commonUtil.notifyError(message)
    return Promise.reject(message)
}

// 业务逻辑错误处理
function bizError(result, config) {
    const code = result.code
    const message = result.message
    if (code === '-1' || '-2' || '-5' || '-4' || '-3') {
        commonUtil.alert(message).onOk(() => {
            location.href = '/logon'
            clear()
        }).onOk(() => {
            // console.log('>>>> second OK catcher')
        }).onCancel(() => {
            // console.log('>>>> Cancel')
        }).onDismiss(() => {
            // console.log('I am triggered on both OK and Cancel')
        })
        return Promise.reject(message)
    } else {
        if (config.showNotify) {
            commonUtil.notifyError(message)
        }
        return Promise.reject(message)
    }
}

// 展示错误响应false
export function showNotifyFalse() {
    return {showNotify: false}
}

export function showNotifyTrue() {
    return {showNotify: true}
}

// post请求
/*export function post(url, data, config) {
    const cfg = Object.assign(showNotifyTrue(), config)
    console.log(cfg + '----------')
    return service({
        url: url,
        method: 'post',
        ...cfg,
        data: data
    })
}*/

// post-upload请求
export function postUpload(url, param, config) {
    // 这里要把content-type设置为multipard/form-data，同时还要设置boundary
    const cfg = Object.assign(showNotifyTrue(), config)
    return service({
        url: url,
        method: 'post',
        ...cfg,
        data: param,
        headers: {
            'Content-Type': 'multipart/form-data; boundary = ' + new Date().getTime()
        }
    })
}

// post请求图片流
export function postForImage(url, param, config) {
    const cfg = Object.assign(showNotifyTrue, config)
    return service({
        url: url,
        method: 'post',
        ...cfg,
        responseType: 'arraybuffer',
        data: param
    }).then(response => {
        return 'data:image/png;base64,' + btoa(
            new Uint8Array(response)
                .reduce((data, byte) => data + String.fromCharCode(byte), '')
        )
    })
}

// get请求
/*
export function get(url, params, config) {
    const cfg = Object.assign(showNotifyTrue, config)
    return service({
        url: url,
        method: 'get',
        ...cfg,
        params: params
    })
}
*/

export function buildFullUrl(url) {
    return BASE_URL + url
}

export default service

/*
import Axios from 'axios'
import Vue from 'vue'
import { Notify } from 'quasar'
import qs from 'qs'

/!**
 * axios 初始化
 *!/
const axios = Axios.create({
  // baseURL: Vue.prototype.$baseURL, // 请求基地址
  timeout: Vue.prototype.$timeOut // 超时时间
})

// 请求拦截器
axios.interceptors.request.use(
  config => {
    const token = sessionStorage.getItem('access_token')
    config.headers.Authorization = 'Bearer ' + token
    if (config.type) {
      switch (config.type) {
        case 'FORM-DATA':
          config.transformRequest = [data => { return 'args=' + JSON.stringify(data) }]
          break
        case 'FORM':
          config.headers['Content-Type'] = 'application/x-www-form-urlencoded'
          config.data = qs.stringify(config.data)
          break
        default:
          break
      }
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// 响应拦截器
axios.interceptors.response.use(
  response => {
    return response
  },
  error => {
    const defaultNotify = {
      message: '未知错误',
      icon: 'warning',
      color: 'warning',
      position: 'top',
      timeout: 1500
    }
    if (error.code === 'ECONNABORTED' || error.message.indexOf('timeout') !== -1 || error.message === 'Network Error') {
      defaultNotify.message = '网络异常'
      Notify.create(defaultNotify)
      return Promise.reject(error)
    }
    switch (error.response.status) {
      case 403:
        defaultNotify.message = '拒绝访问(403)'
        Notify.create(defaultNotify)
        break
      case 404:
        defaultNotify.message = '资源不存在(404)'
        Notify.create(defaultNotify)
        break
      case 408:
        defaultNotify.message = '请求超时(404)'
        Notify.create(defaultNotify)
        break
      case 500:
        defaultNotify.message = '服务器错误(500)'
        Notify.create(defaultNotify)
        break
      case 501:
        defaultNotify.message = '服务未实现(501)'
        Notify.create(defaultNotify)
        break
      case 502:
        defaultNotify.message = '网络错误(502)'
        Notify.create(defaultNotify)
        break
      case 503:
        defaultNotify.message = '服务不可用(503)'
        Notify.create(defaultNotify)
        break
      case 504:
        defaultNotify.message = '网络超时(504)'
        Notify.create(defaultNotify)
        break
      case 505:
        defaultNotify.message = 'HTTP版本不受支持(505)'
        Notify.create(defaultNotify)
        break
      default:
        break
    }
    return Promise.reject(error)
  }
)

export default axios
*/
