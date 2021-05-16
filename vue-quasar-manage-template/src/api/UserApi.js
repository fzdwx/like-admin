import _axios from '../axios/AxiosConfig'
import Vue from 'vue'
import {post,get} from "@/axios/FetchData";

export function getUserRouter() {
    return _axios({
        url: Vue.prototype.$PUBLIC_PATH + 'data/asyncRouterDemo',
        method: 'get',
        responseType: 'text'
    })
}

/**
 * 获取用户信息
 * @returns {AxiosPromise}
 * @param data
 */
export function getUserList(data) {
    return post("/user/page", data);
}