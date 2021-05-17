import {Cookies, LocalStorage, SessionStorage} from 'quasar'

export const localStorageKey = {
    loginData: 'loginData',
    token: 'auth',
    userRole: 'user_role'
}

export function setLoginData(val) {
    SessionStorage.set(localStorageKey.loginData, val)
}

export function getLoginData() {
    return SessionStorage.getItem(localStorageKey.loginData)
}

export function removeToken() {
    LocalStorage.remove(localStorageKey.token)
}

export function setToken(val) {
    set(localStorageKey.token, val)
}

export function getToken() {
    const token = getQueryString(localStorageKey.token)
    if (token) {
        setToken(token)
    }
    return getItem(localStorageKey.token)
}

export function setUserRole(val) {
    set(localStorageKey.userRole, val)
}

export function getUserRoleNames() {
    return getItem(localStorageKey.userRole)
}

function getQueryString(name) {
    const reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)')
    const r = window.location.search.substr(1).match(reg)
    if (r != null) {
        return unescape(r[2])
    }
    return null
}

export function set(key, val) {
    LocalStorage.set(key, val)
  /*  if (isLocalStorageSupported()) {
    } else {
        Cookies.set(key, val, {expires: 24 * 3600})
    }*/
}

export function getItem(key) {
    if (isLocalStorageSupported()) {
        let item = LocalStorage.getItem(key);
        if (item != null) {
            /*let replace = item.replace("__q_strn|", '');
            return replace*/
            return item;
        }
        return null;
    } else {
        return Cookies.get(key)
    }
}

export function getAll() {
    return LocalStorage.getAll()
}

export function getAllKeys() {
    return LocalStorage.getAllKeys()
}

export function getIndex(index) {
    return LocalStorage.getIndex(index)
}

export function getKey(index) {
    return LocalStorage.getKey(index)
}

export function getLength() {
    return LocalStorage.getLength()
}

export function isEmpty() {
    return LocalStorage.isEmpty()
}

export function remove(key) {
    return LocalStorage.remove(key)
}

export function clear() {
    LocalStorage.clear();
    sessionStorage.clear();
    Cookies.remove(localStorageKey.token)
    Cookies.remove(localStorageKey.userRole)
}

// LocalStorage支持检测
function isLocalStorageSupported() {
    let isSupport = true
    try {
        isSupport = window.localStorage
        if (isSupport) {
            const key = 'local_storage_test'
            LocalStorage.set(key, '1')
            const value = LocalStorage.getItem(key)
            if (!value) {
                isSupport = false
            }
            LocalStorage.remove(key)
        } else {
            isSupport = false
        }
    } catch (e) {
        isSupport = false
    }
    return isSupport
    // return true;
}
