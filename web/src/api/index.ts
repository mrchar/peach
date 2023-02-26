import axios from "axios"
import {auth} from "./auth"
import {Response} from "./base"

import {ElMessage} from "element-plus"

axios.defaults.baseURL = "http://localhost:8080/api/"
axios.defaults.withCredentials = true

axios.interceptors.response.use(
    (res) => {
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











