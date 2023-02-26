import axios from "axios"
import {Account, User} from "../types"

interface NameAndPasswordParams {
    name: string,
    password: string
}

export type RegisterParams = NameAndPasswordParams

export const register = async (params: RegisterParams): Promise<Account> => {
    return await axios.post("/register", params) as Account
}

export type LoginParams = NameAndPasswordParams

export const login = async (params: LoginParams): Promise<Account> => {
    return await axios.post("/login", params)
}

export interface RegisterProfileParams {
    name: string
    gender: string,
    birthday: string,
    phoneNumber: string
    smsAuthToken: string
    emailAddress: string | null
}

export async function registerProfile(params: RegisterProfileParams): Promise<User> {
    return axios.put("/self/profile", params)
}

export async function sendSmsAuthToken(phoneNumber: string): Promise<void> {
    return axios.get("/sms/v-code", {params: {phoneNumber: phoneNumber}})
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

export const auth = {
    register,
    login,
    registerProfile,
    sendSmsAuthToken,
    verifySmsAuthToken,
}