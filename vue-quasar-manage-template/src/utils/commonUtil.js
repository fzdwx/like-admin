import {Notify, Dialog, QSpinnerIos, Loading, exportFile} from 'quasar'
import _ from 'lodash'

function getRandomData(datas) {
    return datas[Math.floor(Math.random() * datas.length)]
}

/**
 * 判断value是否为空
 * true 代表为空
 * @param value
 * @returns {boolean}
 */
function strIsEmpty(value) {
    return typeof value == "undefined" || value == null || value === "";
}

function getRandomRangeInt(minValue, maxValue) {
    return getRandomFloorInt(maxValue) + minValue
}

function getRandomFloorInt(maxValue) {
    return Math.floor(Math.random() * maxValue)
}

function getRandomCeilInt(maxValue) {
    return Math.ceil(Math.random() * maxValue)
}

function resetObj(obj) {
    const objClone = _.clone(obj)
    Object.keys(objClone).forEach((key) => {
        objClone[key] = null
    })
    return objClone
}

function resetArray(array) {
    if (array && array instanceof Array) {
        const arrayClone = _.clone(array)
        arrayClone.splice(0, arrayClone.length)
        return arrayClone
    }
    return array
}

/* notify - begin */
function notifySuccess(message) {
    Notify.create({
        color: 'white',
        textColor: 'positive',
        icon: 'check_circle',
        position: 'top',
        message: message
    })
}

function notifyError(message) {
    Notify.create({
        color: 'white',
        textColor: 'negative',
        icon: 'error',
        position: 'top',
        message: message
    })
}

function notifyWaring(message) {
    Notify.create({
        color: 'white',
        textColor: 'warning',
        icon: 'warning',
        position: 'top',
        message: message
    })
}

function notifyAlert(message) {
    Notify.create({
        color: 'white',
        textColor: 'negative',
        icon: 'add_alert',
        position: 'top',
        message: message
    })
}

function notifyInfo(message) {
    Notify.create({
        color: 'white',
        textColor: 'info',
        icon: 'info',
        position: 'top',
        message: message
    })
}

/* notify - end */

/* confirm - begin */
function confirm(message, title) {
    if (!title) {
        title = '提示'
    }
    return Dialog.create({
        title: title,
        message: message,
        cancel: {
            label: '取消',
            color: 'warning',
            unelevated: true
        },
        ok: {
            label: '确认',
            color: 'primary',
            unelevated: true
        },
        persistent: true
    })
}

/* alert - end */

/* confirm - begin */
function alert(message, title) {
    if (!title) {
        title = '系统提示'
    }
    return Dialog.create({
        title: title,
        message: message,
        ok: '确认',
        persistent: true
    })
}

/* alert - end */

function getUrlRootPath() {
    return window.location.protocol + '//' + location.host
}

export function showLoading(msg) {
    if (!msg || msg === '') {
        msg = '加载中'
    }
    const spinner = QSpinnerIos
    Loading.show({
        spinner,
        spinnerSize: 40,
        spinnerColor: 'blue',
        backgroundColor: 'white',
        message: msg,
        messageColor: 'blue'
    })
}

function hideLoading() {
    Loading.hide()
}

function exportTable(columns, datas, separator) {
    if (!separator || separator === '') {
        separator = ','
    }
    // naive encoding to csv format
    const content = [columns.map(col => wrapCsvValue(col.label))].concat(
        datas.map(row => columns.map(col => wrapCsvValue(
            typeof col.field === 'function'
                ? col.field(row)
                // eslint-disable-next-line no-void
                : row[col.field === void 0 ? col.name : col.field],
            col.format
        )).join(separator))
    ).join('\r\n')

    const status = exportFile(
        'table-export.csv',
        '\uFEFF' + content,
        'text/csv'
    )
    if (status !== true) {
        notifyWaring('浏览器拒绝文件导出')
    }
}

function wrapCsvValue(val, formatFn) {
    // eslint-disable-next-line no-void
    let formatted = formatFn !== void 0
        ? formatFn(val)
        : val
    // eslint-disable-next-line no-void
    formatted = formatted === void 0 || formatted === null
        ? ''
        : String(formatted)
    formatted = formatted.split('"').join('""')
    return `"${formatted}"`
}

function uploadFileCheckImage(file) {
    // 上传前做一些事，比如限制文件的格式大小
    const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png'
    if (!isJpgOrPng) {
        notifyAlert("头像必须是JPG或者PNG格式的文件")
    }
    const isLt200KB = file.size / 1024 < 200
    if (!isLt200KB) {
        notifyAlert("头像大小必须小于200KB")
    }
    // // 检查文件格式
    //     check(file) {
    //       if (CommonUtil.uploadFileCheckImage(file[0])) {
    //         return file
    //       }
    //       return null;
    //     },
    return isJpgOrPng && isLt200KB;
}

export default {
    getRandomData,
    getRandomRangeInt,
    getRandomCeilInt,
    getRandomFloorInt,
    getUrlRootPath,
    notifySuccess,
    notifyInfo,
    notifyWaring,
    notifyAlert,
    notifyError,
    confirm,
    alert,
    resetObj,
    resetArray,
    showLoading,
    hideLoading,
    exportTable,
    uploadFileCheckImage
}
