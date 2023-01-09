import axios, {AxiosError, AxiosResponse} from "axios"
import {ElMessage} from "element-plus"

axios.defaults.baseURL = "http://localhost:8080/api/"
axios.defaults.withCredentials = true

axios.interceptors.response.use(
    (res: AxiosResponse) => {
        const response = res.data as Response
        if (response && response.data) {
            return response.data
        }

        return res.data
    },
    (err: AxiosError) => {
        if (err.response) {
            const response = err.response.data as Response
            if (response && response.message) {
                err.message = response.message
                ElMessage.error({message: err.message})
            }
        }

        return Promise.reject(err)
    })

export interface Response {
    code: string | null
    message: string | null
    data: any
}

export interface LoginParams {
    name: string,
    password: string
}

export interface Account {
    number: string,
    name: string,
    user: User | null,
}

export interface User {
    name: string,
    gender: string,
    birthday: Date,
    phoneNumber: string,
    emailAddress: string
}

export const login = async (params: LoginParams): Promise<Account> => {
    return await axios.post("/login", params)
}

export const register = async (params: LoginParams): Promise<Account> => {
    return await axios.post("/register", params) as Account
}

export interface RegisterProfileParams {
    name: string
    gender: string,
    birthday: string,
    phoneNumber: string
    smsAuthToken: string
    emailAddress: string | null
}

export async function registerProfile(params: RegisterProfileParams) {
    return {}
}

export async function sendSmsAuthToken(phoneNumber: string) {
    console.log("发送短信验证码")
    return {}
}

export async function verifySmsAuthToken(phoneNumber: string, smsAuthToken: string): Promise<boolean> {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            if (smsAuthToken === "123456") {
                resolve(true)
            } else {
                resolve(false)
            }
        }, 1000)
    })
}