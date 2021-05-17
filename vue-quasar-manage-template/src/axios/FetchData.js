import _axios, {showNotifyTrue} from './AxiosConfig'
import Vue from 'vue'
import {getToken} from "@/utils/authUtil";

/**
 * 自定义通用 axios 封装类
 * @param query 请求体
 * @returns {*}
 * @author ths
 */

const fetchData = query => {
    const cfg = Object.assign(showNotifyTrue(), query.config)
    return _axios({
        url: query.url, // 请求地址
        method: query.method || 'POST', // 请求方式，默认为 POST
        params: query.params, // 请求参数
        responseType: query.responseType || 'json', // 响应类型，默认为json
        auth: query.auth || {username: getToken()},
        data: query.data || '', // 请求体数据 （仅仅post可用）
        type: query.type, // 自定义请求类型，请看 axios-config.js
        ...cfg
    });
}

Vue.prototype.$fetchData = fetchData


export function get(url, params, config) {
    return fetchData({url, params, method: 'GET'});
}

export function post(url, data, config) {
    return fetchData({url, data, method: 'POST'},config);
}


export default Vue
