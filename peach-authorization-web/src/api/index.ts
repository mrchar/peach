import axios from "axios"
import {auth} from "./auth"
import {Response} from "./base"

import {ElMessage} from "element-plus"

axios.defaults.baseURL = "http://127.0.0.1:8080/api/"
axios.defaults.withCredentials = true

const token = useStorage("token", "")

axios.interceptors.request.use((config) => {
    if (token.value) {
        try {
            // @ts-ignore
            config.headers.set("X-Auth-Token", token.value)
        } catch (e) {
            console.error("向请求中添加用户凭据时发生错误", e)
        }
    }
    return config
})

axios.interceptors.response.use(
    (res) => {
        try {
            // @ts-ignore
            if (res.headers.has("X-Auth-Token")) {
                // @ts-ignore
                token.value = res.headers.get("X-Auth-Token")
            }
        } catch (e) {
            console.error("获取用户凭据时发生错误", e)
        }


        const response = res.data as Response
        if (response && response.data) {
            return response.data
        }

        return res.data
    },
    (err) => {
        if (err.response) {
            const response = err.response.data as Response
            if (response && response.message) {
                err.message = response.message
                ElMessage.error({message: err.message})
            }
        }

        return Promise.reject(err)
    },
)

export const api = {
    auth,
}

export type {
    RegisterParams,
    LoginParams,
    RegisterProfileParams,
} from "./auth"











