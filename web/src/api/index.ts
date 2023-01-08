import axios from "axios"

axios.defaults.baseURL = "http://localhost:8080/api/"
axios.defaults.withCredentials = true

export interface LoginParams {
    name: string,
    password: string
}

export interface Account {
    number: string,
    name: string,
    user: User,
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
    gender: string | null
    birthday: Date | null
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